package com.ruoyi.charity.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.charity.domain.bo.CharityControllerDonatedMaterialsInputBO;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.vo.MaterialInfoVo;
import com.ruoyi.charity.mapper.mp.MPUserMapper;
import com.ruoyi.charity.service.ActivityTraceService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ActivityTraceServiceImpl implements ActivityTraceService {


    @Autowired
    private CharityControllerService charityControllerService;

    @Autowired
    private MPUserMapper mpUserMapper;

    @Override
    public AjaxResult donation(MaterialInfoVo materialInfoVo, String username) {

        CharityControllerDonatedMaterialsInputBO materialsInputBO = new CharityControllerDonatedMaterialsInputBO();
        BeanUtils.copyProperties(materialInfoVo,materialsInputBO);
        try
        {
            TransactionResponse transactionResponse = charityControllerService.donatedMaterials(materialsInputBO);
            if (transactionResponse.getReturnMessage().equals(CharityControllerService.SUCCESS))
            {
                // 获取交易哈希喝区块链高度
                String transactionHash = transactionResponse.getTransactionReceipt().getTransactionHash();
                int blockNumber = Integer.parseInt(transactionResponse.getTransactionReceipt().getBlockNumber().substring(2), 16);

                // 更新当前的用户信息
                CharityUser charityUser = mpUserMapper.selectOne(Wrappers.lambdaQuery(CharityUser.class).eq(CharityUser::getUsername, username));
                Integer oldCredit = charityUser.getCredit();
                charityUser.setCredit(oldCredit + 100);
                mpUserMapper.updateById(charityUser);

                // 通过rabbitmq消息队列通知实现异步处理业务

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
