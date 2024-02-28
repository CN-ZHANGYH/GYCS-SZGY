package com.ruoyi.charity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.charity.domain.CharityRaiseAudit;
import com.ruoyi.charity.domain.bo.CharityControllerInitiateFundRaisingInputBO;
import com.ruoyi.charity.domain.bo.CharityControllerUploadCertificateInputBO;
import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.vo.CertificateInfoVo;
import com.ruoyi.charity.mapper.CharityUserJMapper;
import com.ruoyi.charity.mapper.mp.RaiseFundMapper;
import com.ruoyi.charity.service.ICharityRaiseAuditService;
import com.ruoyi.charity.service.RaiseFundService;
import com.ruoyi.charity.utils.BlockTimeUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import lombok.SneakyThrows;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


@Service
public class RaiseFundServiceImpl implements RaiseFundService {


    @Autowired
    private CharityControllerService charityControllerService;

    @Autowired
    private RaiseFundMapper raiseFundMapper;

    @Autowired
    private CharityUserJMapper charityUserJMapper;

    @Autowired
    private ICharityRaiseAuditService charityRaiseAuditService;

    /**
     * 用户发起公益募资业务
     * @param charityRaiseFund
     * @return AjaxResult
     */
    @SneakyThrows
    @Override
    public AjaxResult initiateRaiseFund(CharityRaiseFund charityRaiseFund) {
        // 查询当前的公益活动是否已经存在
        CharityRaiseFund isRaiseFund = raiseFundMapper.selectOne(Wrappers.lambdaQuery(CharityRaiseFund.class)
                .eq(CharityRaiseFund::getTitle, charityRaiseFund.getTitle())
                .eq(CharityRaiseFund::getPromoterAddress, charityRaiseFund.getPromoterAddress()));
        if (isRaiseFund != null) return AjaxResult.error("当前的公益活动已经存在");

        // 转换当前的时间
        long startTime = BlockTimeUtil.stringToMillis(charityRaiseFund.getStartTime(), "yyyy-MM-dd");
        long endTime = BlockTimeUtil.stringToMillis(charityRaiseFund.getEndTime(), "yyyy-MM-dd");

        // 调用区块链发起公益募资活动
        CharityControllerInitiateFundRaisingInputBO fundRaisingInputBO = getRaisingInputBO(charityRaiseFund, startTime, endTime);
        TransactionResponse transactionResponse = charityControllerService.InitiateFundRaising(fundRaisingInputBO);

        if (transactionResponse.getReturnMessage().equals("Success"))
        {
            // 获取返回的值
            JSONArray result = JSONArray.parseArray(
                    JSONArray.parseArray(transactionResponse.getValues())
                            .get(0)
                            .toString());
            // 插入数据库中
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            charityRaiseFund.setId(result.getLongValue(0));
            charityRaiseFund.setCreateTime(sdf.parse(BlockTimeUtil.convertToDateTime(result.getLongValue(3))));
            charityRaiseFund.setStatus(result.getLongValue(5));
            charityRaiseFund.setTotalPeople(BigInteger.valueOf(result.getLongValue(8)));
            charityRaiseFund.setOverAmount(BigInteger.valueOf(result.getLongValue(9)));
            charityRaiseFund.setWithdrawAmount(BigInteger.valueOf(result.getLongValue(10)));

            // 判断当前是否插入数据库成功
            int status = raiseFundMapper.insert(charityRaiseFund);
            if (status > 0)
            {
                return AjaxResult.success().put("msg","发起公益活动成功");
            }
        }
        return AjaxResult.error().put("msg","发起公益活动失败");
    }


    /**
     * 上传证明信息接口
     * @param certificateInfoVo
     * @param username
     * @return
     */
    @Override
    public AjaxResult uploadCertificate(CertificateInfoVo certificateInfoVo, String username) {

        // 根据用户名查询用户的地址然后根据公益募资的活动编号和用户的区块链账户地址进行查询详细的信息
        // SELECT * FROM charity_raise_fund WHERE id = '19' and promoter_address = (SELECT user_address FROM charity_user WHERE username = 'mmm');
        CharityUser charityUser = charityUserJMapper.selectOne(Wrappers
                .lambdaQuery(CharityUser.class)
                .eq(CharityUser::getUsername, username));
        CharityRaiseFund isRaiseFund = raiseFundMapper.selectOne(Wrappers.lambdaQuery(CharityRaiseFund.class)
                .eq(CharityRaiseFund::getId, certificateInfoVo.getRaiseId())
                .eq(CharityRaiseFund::getPromoterAddress, charityUser.getUserAddress()));

        if (isRaiseFund != null) {
            // 如果不是空的 直接进行证明上链 然后需要进行审批
            CharityControllerUploadCertificateInputBO certificateInputBO = new CharityControllerUploadCertificateInputBO();
            certificateInputBO.set_raiseId(BigInteger.valueOf(certificateInfoVo.getRaiseId()));
            certificateInputBO.set_CertificateInfo(JSON.toJSONString(certificateInfoVo));
            System.out.println(certificateInputBO);
            try
            {
                TransactionResponse transactionResponse = charityControllerService.uploadCertificate(certificateInputBO);
                if (transactionResponse.getReturnMessage().equals("Success"))
                {
                    // 如果交易成功就添加一条申请记录到审核中
                    CharityRaiseAudit charityRaiseAudit = new CharityRaiseAudit();
                    charityRaiseAudit.setRaiseId(certificateInfoVo.getRaiseId());
                    charityRaiseAudit.setStatus(1);
                    charityRaiseAudit.setApplyTime(new Date());
                    charityRaiseAuditService.insertCharityRaiseAudit(charityRaiseAudit);

                    // 上传成功
                    return AjaxResult.success().put("msg","上传成功");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return AjaxResult.error().put("msg","上传失败");
    }

    private static CharityControllerInitiateFundRaisingInputBO getRaisingInputBO(CharityRaiseFund charityRaiseFund, long startTime, long endTime) {
        CharityControllerInitiateFundRaisingInputBO fundRaisingInputBO = new CharityControllerInitiateFundRaisingInputBO();
        fundRaisingInputBO.set_title(charityRaiseFund.getTitle());
        fundRaisingInputBO.set_desc(charityRaiseFund.getDescription());
        fundRaisingInputBO.set_promoterAddress(charityRaiseFund.getPromoterAddress());
        fundRaisingInputBO.set_totalAmount(charityRaiseFund.getTotalAmount());
        fundRaisingInputBO.set_startTime(BigInteger.valueOf(startTime));
        fundRaisingInputBO.set_endTime(BigInteger.valueOf(endTime));
        return fundRaisingInputBO;
    }
}
