package com.ruoyi.charity.listener;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.charity.domain.bo.CharityControllerInit_userInputBO;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.domain.vo.RegisterVo;
import com.ruoyi.charity.domain.vo.UserKey;
import com.ruoyi.charity.service.ICharityUserService;
import com.ruoyi.charity.service.UserKeyService;
import com.ruoyi.charity.service.impl.CharityControllerService;
import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zyh
 * @date 2024/2/26 11:47
 * @desc IntelliJ IDEA
 */

@Slf4j
@Component
@RabbitListener(queues = "REGISTER_QUEUE")//监听的队列名称 TestDirectQueue
public class RegisterDirectListener {

    @Autowired
    private CharityControllerService charityControllerService;

    @Autowired
    private UserKeyService userKeyService;

    @Autowired
    private ICharityUserService charityUserService;

    @RabbitHandler
    public void process(String message) {

        MessageResult messageResult = JSONObject.parseObject(message, MessageResult.class);
        SysUser sysUser = JSONObject.parseObject(messageResult.getData(),SysUser.class);
        System.out.println("DirectReceiver消费者收到消息  : " + messageResult);

        // 这里进行调用当前的区块链注册
        UserKey blockChainUser = userKeyService.createBlockChainUser();
        CharityControllerInit_userInputBO init_userInputBO = new CharityControllerInit_userInputBO();
        init_userInputBO.set_userAddress(blockChainUser.getUserAddress());
        System.out.println(blockChainUser.getUserAddress());
        init_userInputBO.set_userName(sysUser.getUserName());
        init_userInputBO.set_designation(sysUser.getNickName());
        init_userInputBO.set_cardId(sysUser.getUserName());
        try
        {
            TransactionResponse transactionResponse = charityControllerService.init_user(init_userInputBO);
            if (transactionResponse.getReturnMessage().equals("Success"))
            {
             log.info("新用户注册成功： {}",sysUser.getUserName());

                CharityUser charityUser = new CharityUser();
                charityUser.setId(sysUser.getUserId());
                charityUser.setAmount(0L);
                charityUser.setCardId(sysUser.getUserName());
                charityUser.setCredit(String.valueOf(0));
                charityUser.setUserAddress(blockChainUser.getUserAddress());
                charityUser.setPrivateKey(blockChainUser.getPrivateKey());
                charityUser.setPublicKey(blockChainUser.getPublicKey());
                charityUser.setDesignation(sysUser.getUserName());
                charityUser.setVoteCount(0L);
                charityUser.setWithdrawCount(0L);
                charityUserService.insertCharityUser(charityUser);

            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
