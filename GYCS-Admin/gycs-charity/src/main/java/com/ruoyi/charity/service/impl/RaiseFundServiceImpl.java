package com.ruoyi.charity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.charity.domain.bo.*;
import com.ruoyi.charity.domain.dto.CharityRaiseAudit;
import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.vo.CertificateInfoVo;
import com.ruoyi.charity.domain.vo.RaiseFundAuditVo;
import com.ruoyi.charity.domain.vo.VoteInfo;
import com.ruoyi.charity.mapper.join.CharityUserJMapper;
import com.ruoyi.charity.mapper.join.RaiseFundJMapper;
import com.ruoyi.charity.mapper.mp.RaiseAuditMapper;
import com.ruoyi.charity.mapper.mp.RaiseFundMapper;
import com.ruoyi.charity.service.ICharityRaiseAuditService;
import com.ruoyi.charity.service.RaiseFundService;
import com.ruoyi.charity.utils.BlockTimeUtil;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import lombok.SneakyThrows;
import org.apache.poi.xssf.model.MapInfo;
import org.aspectj.weaver.loadtime.Aj;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


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

    @Autowired
    private RaiseAuditMapper raiseAuditMapper;

    @Autowired
    private RaiseFundJMapper raiseFundJMapper;


    @Autowired
    private RedisCache redisCache;

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

        // 查询当前是否有审批 有则说明已经上传过了 需要提示已经上传证明
        CharityRaiseAudit isRaiseAudit = raiseAuditMapper.selectOne(Wrappers.lambdaQuery(CharityRaiseAudit.class)
                .eq(CharityRaiseAudit::getRaiseId, certificateInfoVo.getRaiseId()));
        if (isRaiseAudit != null) return AjaxResult.error().put("msg","当前已经上传了证明");

        // 根据用户名查询用户的地址然后根据公益募资的活动编号和用户的区块链账户地址进行查询详细的信息
        // SELECT * FROM charity_raise_fund WHERE id = '19' and promoter_address = (SELECT user_address FROM charity_user WHERE username = 'mmm');
        CharityUser charityUser = queryCharityUserByUsername(username);
        CharityRaiseFund isRaiseFund = raiseFundMapper.selectOne(Wrappers.lambdaQuery(CharityRaiseFund.class)
                .eq(CharityRaiseFund::getId, certificateInfoVo.getRaiseId())
                .eq(CharityRaiseFund::getPromoterAddress, charityUser.getUserAddress()));

        // 如果查询的结果不是空的说明已经发起了公益募资活动
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
                    charityRaiseAudit.setApply_status(1);
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

    private CharityUser queryCharityUserByUsername(String username) {
        CharityUser charityUser = charityUserJMapper.selectOne(Wrappers
                .lambdaQuery(CharityUser.class)
                .eq(CharityUser::getUsername, username));
        return charityUser;
    }

    /**
     * 查询发布的公益募资活动详细信息和上传的证明信息以及审批信息
     *
     * @param raiseId
     * @param username
     * @return
     */
    @SneakyThrows
    @Override
    public AjaxResult getRaiseFundInfo(Long raiseId, String username) {
        // 默认如果没有数据直接查询Redis读取缓存数据
        String raise_fund_audit_value = redisCache.getCacheObject(CacheConstants.RAISE_FUND_AUDIT_KEY + username);
        HashMap result = JSONObject.parseObject(raise_fund_audit_value, HashMap.class);
        if (raise_fund_audit_value != null) {
            AjaxResult success = AjaxResult.success();
            success.put("data",result);
            return success;
        }
        // 多表联查当前的公益募资活动信息和审批信息
        CharityUser charityUser = queryCharityUserByUsername(username);
        MPJLambdaWrapper<CharityRaiseFund> lambdaWrapper = new MPJLambdaWrapper<CharityRaiseFund>()
                .eq(CharityRaiseFund::getPromoterAddress, charityUser.getUserAddress())
                .selectAll(CharityRaiseFund.class)
                .select(
                        CharityRaiseAudit::getRaiseId,
                        CharityRaiseAudit::getApply_status,
                        CharityRaiseAudit::getUsername,
                        CharityRaiseAudit::getApplyTime,
                        CharityRaiseAudit::getAuditTime
                ).leftJoin(CharityRaiseAudit.class, CharityRaiseAudit::getRaiseId, CharityRaiseFund::getId);

        RaiseFundAuditVo raiseFundAuditVo = raiseFundJMapper.selectJoinOne(RaiseFundAuditVo.class, lambdaWrapper);

        // 调用查询区块链的信息 查询区块链的上传的证明信息
        CharityControllerGetCertificateInfoDetailInputBO detailInputBO = new CharityControllerGetCertificateInfoDetailInputBO();
        detailInputBO.set_raiseId(BigInteger.valueOf(raiseId));
        CallResponse callResponse = charityControllerService.getCertificateInfoDetail(detailInputBO);
        if (callResponse.getReturnMessage().equals("Success")) {
            String data = JSONArray.parseArray(callResponse.getValues()).get(0).toString();
            CertificateInfoVo certificateInfoVo = JSONObject.parseObject(data, CertificateInfoVo.class);

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("raise_info",raiseFundAuditVo);
            hashMap.put("certificate_info",certificateInfoVo);

            // 存储到redis缓存中 用户在第一次查询的时候是需要久一点获取数据，当用户第二次获取数据的时候可以直接从redis中读取缓存
            redisCache.setCacheObject(CacheConstants.RAISE_FUND_AUDIT_KEY + username,JSON.toJSONString(hashMap),5, TimeUnit.MINUTES);
            AjaxResult success = AjaxResult.success();
            success.put("data",hashMap);
            return success;
        }
        return AjaxResult.error().put("msg","查询失败");
    }

    /**
     * 根据ID查询对应公益活动的上传证明信息
     *
     * @param raiseId
     * @param username
     * @return AjaxResult
     */
    @Override
    public AjaxResult getCertificateInfo(Long raiseId, String username) {
        // 默认先查询redis的缓存中是否有该数据
        CertificateInfoVo certificateInfoVoResult = redisCache.getCacheObject(CacheConstants.CERTIFICATE_INFO_KEY + username);
        if (certificateInfoVoResult != null) {
            AjaxResult success = AjaxResult.success();
            success.put("data",certificateInfoVoResult);
            success.put("msg","查询成功");
            return success;
        }

        // 调用区块链查询当前的上传证明信息
        CharityControllerGetCertificateInfoDetailInputBO detailInputBO = new CharityControllerGetCertificateInfoDetailInputBO();
        detailInputBO.set_raiseId(BigInteger.valueOf(raiseId));
        try
        {
            CallResponse callResponse = charityControllerService.getCertificateInfoDetail(detailInputBO);
            if (callResponse.getReturnMessage().equals("Success")) {
                String data = JSONArray.parseArray(callResponse.getValues()).get(0).toString();
                CertificateInfoVo certificateInfoVo = JSONObject.parseObject(data, CertificateInfoVo.class);

                // 第一次查询直接存储到Redis缓存中
                redisCache.setCacheObject(CacheConstants.CERTIFICATE_INFO_KEY + username,certificateInfoVo,5,TimeUnit.MINUTES);
                AjaxResult success = AjaxResult.success();
                success.put("data",certificateInfoVo);
                success.put("msg","查询成功");
                return success;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error().put("msg","查询失败");

    }


    /**
     * 查询当前的公益募资活动的投票状态和详细信息
     * @param raiseId
     * @param username
     * @return
     */
    @Override
    public AjaxResult getRaiseFundVoteStatus(Long raiseId, String username) {

        // 先从redis中进行查询
        VoteInfo voteInfoResult = redisCache.getCacheObject(CacheConstants.VOTE_INFO + username);
        if (voteInfoResult != null) {
            AjaxResult success = AjaxResult.success();
            success.put("data",voteInfoResult);
            success.put("msg","查询成功");
            return success;
        }

        // 如果redis中没有则需要重新查询区块链上的数据
        CharityControllerGetVoteInfoInputBO infoInputBO = new CharityControllerGetVoteInfoInputBO();
        infoInputBO.set_raiseId(BigInteger.valueOf(raiseId));

        try
        {
            CallResponse callResponse = charityControllerService.getVoteInfo(infoInputBO);
            if (callResponse.getReturnMessage().equals("Success")) {

                JSONArray result = JSONArray.parseArray(callResponse.getValues());
                VoteInfo voteInfo = new VoteInfo();
                voteInfo.setIsYes(result.getBigInteger(0));
                voteInfo.setIsNo(result.getBigInteger(1));
                voteInfo.setIsTrue(result.getBoolean(2));

                // 存储到redis中
                redisCache.setCacheObject(CacheConstants.VOTE_INFO + username,voteInfo,5,TimeUnit.MINUTES);
                AjaxResult success = AjaxResult.success();
                success.put("data",voteInfo);
                success.put("msg","查询成功");
                return success;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error().put("msg","查询失败");
    }

    /**
     * 参与投票
     * @param raiseId
     * @param status
     * @param username
     * @return
     */
    @Override
    public AjaxResult voteOfRaiseFund(Long raiseId, Boolean status, String username) {
        CharityUser charityUser = queryCharityUserByUsername(username);

        CharityControllerVoteOfRaiseFundInputBO voteInfo = new CharityControllerVoteOfRaiseFundInputBO();
        voteInfo.set_raiseId(BigInteger.valueOf(raiseId));
        voteInfo.set_flag(status);
        voteInfo.set_userAddress(charityUser.getUserAddress());
        try
        {
            TransactionResponse transactionResponse = charityControllerService.voteOfRaiseFund(voteInfo);
            if (transactionResponse.getReturnMessage().equals("Success")) {
                Boolean isStatus = (Boolean) JSONArray.parseArray(transactionResponse.getValues()).get(0);
                if (isStatus){
                    return AjaxResult.success().put("msg","投票成功");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error().put("msg","投票失败");
    }

    @Override
    public AjaxResult getRaiseFundDetail(Long raiseId) {
        CharityRaiseFund charityRaiseFund = raiseFundMapper.selectById(raiseId);
        if (charityRaiseFund != null) {
            AjaxResult success = AjaxResult.success();
            success.put("data",charityRaiseFund);
            success.put("msg","查询成功");
            return  success;
        }
        return AjaxResult.error().put("msg","查询失败");
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
