package com.ruoyi.charity.listener;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.charity.domain.bo.CharityControllerInit_userInputBO;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.domain.vo.UserKey;
import com.ruoyi.charity.service.ICharityUserService;
import com.ruoyi.charity.service.UserKeyService;
import com.ruoyi.charity.service.impl.CharityControllerService;
import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zyh
 * @date 2024/2/26 11:47
 * @desc IntelliJ IDEA
 */

@Slf4j
@Component
// bindings其实就是用来确定队列和交换机绑定关系
@RabbitListener(bindings =@QueueBinding(
        value = @Queue(value = "register.direct.queue",autoDelete = "false"),
        exchange = @Exchange(value = "direct_register_exchange", type = ExchangeTypes.DIRECT),
        key = {"REGISTER"}
))
public class RegisterDirectListener {

    @Autowired
    private CharityControllerService charityControllerService;

    @Autowired
    private UserKeyService userKeyService;

    @RabbitHandler
    public void process(String message) {
        // 接收key为register的订阅模式交换机发来的消息进行消费
        MessageResult messageResult = JSONObject.parseObject(message, MessageResult.class);
        SysUser sysUser = JSONObject.parseObject(messageResult.getData(),SysUser.class);
        System.out.println("DirectReceiver消费者收到消息  : " + messageResult);

        // 这里才是进行调用当前的区块链注册
        UserKey blockChainUser = userKeyService.createBlockChainUser();
        CharityControllerInit_userInputBO init_userInputBO = new CharityControllerInit_userInputBO();
        init_userInputBO.set_userAddress(blockChainUser.getUserAddress());
        init_userInputBO.set_userName(sysUser.getUserName());
        init_userInputBO.set_designation(sysUser.getNickName());
        init_userInputBO.set_cardId(sysUser.getUserName());
        try
        {
            TransactionResponse transactionResponse = charityControllerService.init_user(init_userInputBO);
            if (transactionResponse.getReturnMessage().equals(CharityControllerService.SUCCESS))
            {
                // 进行日志捕获
                log.info("新用户注册成功： {}",sysUser.getUserName());
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
