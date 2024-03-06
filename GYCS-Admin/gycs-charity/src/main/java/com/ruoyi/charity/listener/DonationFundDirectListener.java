package com.ruoyi.charity.listener;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.charity.domain.bo.CharityControllerDonatedFundsInputBO;
import com.ruoyi.charity.domain.bo.CharityControllerUpdateFundRaisingStatusInputBO;
import com.ruoyi.charity.domain.dto.CharityRaiseAudit;
import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.charity.domain.dto.DonationTrace;
import com.ruoyi.charity.domain.vo.DonatedFundVo;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.service.ICharityRaiseFundService;
import com.ruoyi.charity.service.IDonationTraceService;
import com.ruoyi.charity.service.impl.CharityControllerService;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author zyh
 * @date 2024/2/26 11:47
 * @desc IntelliJ IDEA
 */

@Slf4j
@Component
// bindings其实就是用来确定队列和交换机绑定关系
@RabbitListener(bindings =@QueueBinding(
        value = @Queue(value = "donation.direct.queue",autoDelete = "false"),
        exchange = @Exchange(value = "direct_donation_fund_exchange", type = ExchangeTypes.DIRECT),
        key = {"DONATION_FUND_KEY"}
))
public class DonationFundDirectListener {

    @Autowired
    private CharityControllerService charityControllerService;

    @Autowired
    private IDonationTraceService donationTraceService;

    @RabbitHandler
    public void process(String message) {
        // 接收key为register的订阅模式交换机发来的消息进行消费
        MessageResult messageResult = JSONObject.parseObject(message, MessageResult.class);
        log.info("DirectReceiver消费者收到消息  : {}" + messageResult.getMessage());

        DonatedFundVo donatedFundVo = JSONObject.parseObject(messageResult.getData(), DonatedFundVo.class);
        System.out.println(donatedFundVo);
        CharityControllerDonatedFundsInputBO fundsInputBO = new CharityControllerDonatedFundsInputBO();
        BeanUtils.copyProperties(donatedFundVo,fundsInputBO);
        try
        {
            TransactionResponse transactionResponse = charityControllerService.donatedFunds(fundsInputBO);
            if (transactionResponse.getReturnMessage().equals("Success"))
            {
                DonationTrace donationTrace = new DonationTrace();
                donationTrace.setIsDonation(true);
                donationTrace.setDonorAddress(donatedFundVo.get_donorAddress());
                donationTrace.setDestAddress(donatedFundVo.get_destAddress());
                donationTrace.setTransTime(new Date());
                donationTrace.setTransType(donatedFundVo.get_transType());
                donationTrace.setSource(donatedFundVo.get_source());
                donationTrace.setRaiseId(donatedFundVo.get_raiseId());
                donationTrace.setAmount(donatedFundVo.get_amount());
                donationTrace.setDescription(donatedFundVo.get_desc());
                donationTraceService.insertDonationTrace(donationTrace);

                log.info("用户捐款成功： {}",donatedFundVo.get_donorAddress());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
