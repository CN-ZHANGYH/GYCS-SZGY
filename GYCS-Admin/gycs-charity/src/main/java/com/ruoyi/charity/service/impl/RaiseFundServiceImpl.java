package com.ruoyi.charity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.charity.domain.bo.CharityControllerInitiateFundRaisingInputBO;
import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.charity.service.RaiseFundService;
import com.ruoyi.charity.utils.BlockTimeUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import lombok.SneakyThrows;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.Oneway;
import javax.xml.transform.Source;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class RaiseFundServiceImpl implements RaiseFundService {


    @Autowired
    private CharityControllerService charityControllerService;

    /**
     * 用户发起公益募资业务
     * @param charityRaiseFund
     * @return AjaxResult
     */
    @SneakyThrows
    @Override
    public AjaxResult initiateRaiseFund(CharityRaiseFund charityRaiseFund) {

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
            CharityRaiseFund charityRaiseFundDto = new CharityRaiseFund();

            System.out.println(result);
            System.out.println(result.get(0));


        }
        return null;
    }

    private static CharityControllerInitiateFundRaisingInputBO getRaisingInputBO(CharityRaiseFund charityRaiseFund, long startTime, long endTime) {
        CharityControllerInitiateFundRaisingInputBO fundRaisingInputBO = new CharityControllerInitiateFundRaisingInputBO();
        fundRaisingInputBO.set_title(charityRaiseFund.getTitle());
        fundRaisingInputBO.set_desc(charityRaiseFund.getDesc());
        fundRaisingInputBO.set_promoterAddress(charityRaiseFund.getPromoterAddress());
        fundRaisingInputBO.set_totalAmount(BigInteger.valueOf(charityRaiseFund.getTotalAmount()));
        fundRaisingInputBO.set_startTime(BigInteger.valueOf(startTime));
        fundRaisingInputBO.set_endTime(BigInteger.valueOf(endTime));
        return fundRaisingInputBO;
    }
}
