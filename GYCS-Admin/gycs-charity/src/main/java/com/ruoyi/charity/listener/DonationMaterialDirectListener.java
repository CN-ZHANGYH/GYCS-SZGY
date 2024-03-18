package com.ruoyi.charity.listener;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.charity.domain.bo.CharityControllerDonatedFundsInputBO;
import com.ruoyi.charity.domain.dto.ActiviteTrace;
import com.ruoyi.charity.domain.dto.ActiviteTransaction;
import com.ruoyi.charity.domain.vo.DonatedFundVo;
import com.ruoyi.charity.domain.vo.MaterialInfoVo;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.mapper.mp.MPActiviteTransactionMapper;
import com.ruoyi.charity.mapper.mp.MPActivityTraceMapper;
import com.ruoyi.charity.utils.BlockTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Component
// bindings其实就是用来确定队列和交换机绑定关系
@RabbitListener(bindings =@QueueBinding(
        value = @Queue(value = "material.direct.queue",autoDelete = "false"),
        exchange = @Exchange(value = "direct_donation_material_exchange", type = ExchangeTypes.DIRECT),
        key = {"ACTIVITY_DONATION_MATERIAL_KEY"}
))
public class DonationMaterialDirectListener {


    @Autowired
    private MPActiviteTransactionMapper mpActiviteTransactionMapper;

    @Autowired
    private MPActivityTraceMapper mpActivityTraceMapper;

    @RabbitHandler
    public void process(String message) {
        // 接收key为register的订阅模式交换机发来的消息进行消费
        MessageResult messageResult = JSONObject.parseObject(message, MessageResult.class);
        log.info("DirectReceiver消费者收到消息  : {}" + messageResult.getMessage());

        HashMap dataMap = JSONObject.parseObject(messageResult.getData(), HashMap.class);

        // 更新当前的用户的捐赠物资交易的区块链交易哈希以及区块链高度
        String transactionHash = (String) dataMap.get("transactionHash");
        Integer blockNumber = (Integer) dataMap.get("blockNumber");
        Integer traceId = (Integer) dataMap.get("traceId");
        MaterialInfoVo materialInfo = JSONObject.parseObject(dataMap.get("materialInfo").toString(),MaterialInfoVo.class);

        ActiviteTransaction activiteTransaction = new ActiviteTransaction();
        activiteTransaction.setTransactionHash(transactionHash);
        activiteTransaction.setStatus(true);
        activiteTransaction.setBlockNumber(BigInteger.valueOf(blockNumber));
        activiteTransaction.setActiviteId(BigInteger.valueOf(traceId));
        mpActiviteTransactionMapper.insert(activiteTransaction);

        // 更新当前的用户的公益溯源信息
        ActiviteTrace activiteTrace = new ActiviteTrace();
        activiteTrace.setCharityId(Long.valueOf(String.valueOf(traceId)));
        activiteTrace.setMaterialType(materialInfo.get_materialType());
        activiteTrace.setMaterialName(materialInfo.get_materialName());
        activiteTrace.setMaterialCount(materialInfo.get_materialCount());
        activiteTrace.setSourceAddress(materialInfo.get_userAddress());
        activiteTrace.setLogisticAddress(materialInfo.get_logisticAddress());
        activiteTrace.setDestAddress(materialInfo.get_destAddress());
        activiteTrace.setIsSign(false);
        activiteTrace.setStatus(1);
        activiteTrace.setActivitId(materialInfo.get_activiteId());
        activiteTrace.setTransTime(BlockTimeUtil.convertToDateTime(System.currentTimeMillis()));

        ArrayList<String> traceAddress = new ArrayList<>();
        ArrayList<String> traceTime = new ArrayList<>();
        traceAddress.add(materialInfo.get_userAddress());
        traceAddress.add(materialInfo.get_logisticAddress());
        traceAddress.add(materialInfo.get_destAddress());
        traceTime.add(BlockTimeUtil.convertToDateTime(System.currentTimeMillis()));
        activiteTrace.setTraceAddress(JSONObject.toJSONString(traceAddress));
        activiteTrace.setTraceTime(JSONObject.toJSONString(traceTime));
        mpActivityTraceMapper.insert(activiteTrace);
    }
}
