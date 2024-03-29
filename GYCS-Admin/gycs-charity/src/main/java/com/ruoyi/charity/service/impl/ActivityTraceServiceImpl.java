package com.ruoyi.charity.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.charity.domain.bo.CharityControllerDonatedMaterialsInputBO;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.vo.MaterialInfoVo;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.mapper.mp.MPUserMapper;
import com.ruoyi.charity.service.ActivityTraceService;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;


@Service
public class ActivityTraceServiceImpl implements ActivityTraceService {


    @Autowired
    private CharityControllerService charityControllerService;

    @Autowired
    private MPUserMapper mpUserMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
                Integer traceId = JSONArray.parseArray(transactionResponse.getValues()).getIntValue(1);


                // 更新当前的用户信息
                CharityUser charityUser = mpUserMapper.selectOne(Wrappers.lambdaQuery(CharityUser.class).eq(CharityUser::getUsername, username));
                Integer oldCredit = charityUser.getCredit();
                charityUser.setCredit(oldCredit + 100);
                mpUserMapper.updateById(charityUser);

                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("transactionHash",transactionHash);
                dataMap.put("blockNumber",blockNumber);
                dataMap.put("traceId",traceId);
                dataMap.put("materialInfo",JSONObject.toJSONString(materialInfoVo));

                // 通过rabbitmq消息队列通知实现异步处理业务
                MessageResult messageResult = new MessageResult();
                messageResult.setCode(HttpStatus.SUCCESS);
                messageResult.setMessage("用户发起了物资捐赠请求");
                messageResult.setData(JSONObject.toJSONString(dataMap));
                rabbitTemplate.convertAndSend("direct_donation_material_exchange","ACTIVITY_DONATION_MATERIAL_KEY",JSONObject.toJSONString(messageResult));

                return AjaxResult.success().put("msg","用户捐赠物资请求成功");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error().put("msg","用户捐赠物资失败");
    }
}
