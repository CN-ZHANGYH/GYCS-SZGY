package com.ruoyi.charity.listener;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.charity.domain.bo.CharityControllerInit_userInputBO;
import com.ruoyi.charity.domain.bo.CharityControllerUpdateFundRaisingStatusInputBO;
import com.ruoyi.charity.domain.dto.CharityRaiseAudit;
import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.domain.vo.UserKey;
import com.ruoyi.charity.mapper.CharityRaiseFundMapper;
import com.ruoyi.charity.service.ICharityRaiseFundService;
import com.ruoyi.charity.service.RaiseFundService;
import com.ruoyi.charity.service.UserKeyService;
import com.ruoyi.charity.service.impl.CharityControllerService;
import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * @author zyh
 * @date 2024/2/26 11:47
 * @desc IntelliJ IDEA
 */

@Slf4j
@Component
// bindings其实就是用来确定队列和交换机绑定关系
@RabbitListener(bindings =@QueueBinding(
        value = @Queue(value = "audit.direct.queue",autoDelete = "false"),
        exchange = @Exchange(value = "direct_raise_audit_exchange", type = ExchangeTypes.DIRECT),
        key = {"RAISE_AUDIT_ROUTING_KEY"}
))
public class RaiseAuditDirectListener {

    @Autowired
    private CharityControllerService charityControllerService;

    @Autowired
    private ICharityRaiseFundService charityRaiseFundService;

    @RabbitHandler
    public void process(String message) {
        // 接收key为register的订阅模式交换机发来的消息进行消费
        MessageResult messageResult = JSONObject.parseObject(message, MessageResult.class);
        System.out.println("DirectReceiver消费者收到消息  : " + messageResult);

        CharityRaiseAudit raiseAudit = JSONObject.parseObject(messageResult.getData(), CharityRaiseAudit.class);
        System.out.println(raiseAudit);

        // 更新同步RaiseFund的数据库中的状态
        CharityRaiseFund charityRaiseFund = charityRaiseFundService.selectCharityRaiseFundById(raiseAudit.getRaiseId());
        charityRaiseFund.setStatus(BigInteger.valueOf(3));
        charityRaiseFundService.updateCharityRaiseFund(charityRaiseFund);

        // 更新区块链上的RaiseFund活动的状态
        CharityControllerUpdateFundRaisingStatusInputBO statusInputBO = new CharityControllerUpdateFundRaisingStatusInputBO();
        statusInputBO.set_raiseId(BigInteger.valueOf(raiseAudit.getRaiseId()));
        statusInputBO.set_status(BigInteger.valueOf(3));

        try
        {
            TransactionResponse transactionResponse = charityControllerService.updateFundRaisingStatus(statusInputBO);
            if (transactionResponse.getReturnMessage().equals("Success")) {
                // 记录当前的区块链交易日志
                log.info("当前的审核交易已经完成上链：{}",raiseAudit);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
