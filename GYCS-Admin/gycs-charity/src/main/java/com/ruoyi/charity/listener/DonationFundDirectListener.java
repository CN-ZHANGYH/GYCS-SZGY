package com.ruoyi.charity.listener;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.charity.domain.bo.CharityControllerDonatedFundsInputBO;
import com.ruoyi.charity.domain.dto.*;
import com.ruoyi.charity.domain.vo.DonatedFundVo;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.mapper.mp.MPDonationTransactionMapper;
import com.ruoyi.charity.mapper.mp.MPUserMapper;
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

    @Autowired
    private ICharityRaiseFundService raiseFundService;


    @Autowired
    private MPUserMapper MPUserMapper;

    @Autowired
    private MPDonationTransactionMapper MPDonationTransactionMapper;

    @RabbitHandler
    public void process(String message) {
        // 接收key为register的订阅模式交换机发来的消息进行消费
        MessageResult messageResult = JSONObject.parseObject(message, MessageResult.class);
        log.info("DirectReceiver消费者收到消息  : {}" + messageResult.getMessage());

        DonatedFundVo donatedFundVo = JSONObject.parseObject(messageResult.getData(), DonatedFundVo.class);

        CharityControllerDonatedFundsInputBO fundsInputBO = new CharityControllerDonatedFundsInputBO();
        BeanUtils.copyProperties(donatedFundVo,fundsInputBO);
        try
        {
            TransactionResponse transactionResponse = charityControllerService.donatedFunds(fundsInputBO);
            if (transactionResponse.getReturnMessage().equals(CharityControllerService.SUCCESS))
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

                // update raise fund active info
                BigInteger amount = donatedFundVo.get_amount();
                CharityRaiseFund charityRaiseFund = raiseFundService
                        .selectCharityRaiseFundById(Long.valueOf(String.valueOf(donatedFundVo.get_raiseId())));
                BigInteger overAmount = charityRaiseFund.getOverAmount();
                BigInteger totalPeople = charityRaiseFund.getTotalPeople();
                charityRaiseFund.setOverAmount(overAmount.add(amount));
                charityRaiseFund.setTotalPeople(totalPeople.add(BigInteger.valueOf(1)));
                raiseFundService.updateCharityRaiseFund(charityRaiseFund);

                // there need update credit of user
                CharityUser charityUser = MPUserMapper
                        .selectOne(Wrappers.lambdaQuery(CharityUser.class).eq(CharityUser::getUserAddress, donatedFundVo.get_donorAddress()));
                Integer credit = charityUser.getCredit();
                BigInteger oldBalance = charityUser.getAmount();
                charityUser.setAmount(oldBalance.subtract(donatedFundVo.get_amount()));
                charityUser.setCredit(credit + 50);
                MPUserMapper.updateById(charityUser);

                // update transaction and blockNumber by this donation
                DonationTransaction donationTransaction = new DonationTransaction();
                donationTransaction.setRaiseId(donatedFundVo.get_raiseId());
                donationTransaction.setStatus(true);
                donationTransaction.setBlockNumber(BigInteger.valueOf(Integer.parseInt(transactionResponse.getTransactionReceipt().getBlockNumber().substring(2), 16)));
                donationTransaction.setTransactionHash(transactionResponse.getTransactionReceipt().getTransactionHash());

                MPDonationTransactionMapper.insert(donationTransaction);

                log.info("用户捐款成功： {}",donatedFundVo.get_donorAddress());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
