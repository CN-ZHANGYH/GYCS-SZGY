package com.ruoyi.charity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.dto.DonationTrace;
import com.ruoyi.charity.domain.dto.Logistic;
import com.ruoyi.charity.domain.vo.BankTransferDataView;
import com.ruoyi.charity.domain.vo.OrderDataVo;
import com.ruoyi.charity.domain.vo.OrderStatusVo;
import com.ruoyi.charity.domain.vo.TransWeekVo;
import com.ruoyi.charity.mapper.mp.*;
import com.ruoyi.charity.service.StatisticsService;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zyh
 * @date 2024/3/27 11:39
 * @desc IntelliJ IDEA
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MPUserMapper mpUserMapper;


    @Autowired
    private MPOrgMapper mpOrgMapper;

    @Autowired
    private MPLogisticMapper mpLogisticMapper;

    @Autowired
    private MPActivityOrderMapper mpActivityOrderMapper;

    @Autowired
    private MPDonationTraceMapper mpDonationTraceMapper;

    @Autowired
    private MPBankTransferRecordMapper mpBankTransferRecordMapper;

    @Override
    public AjaxResult selectTransactionTypeTotalData() {
        Map<String,Object> resultMap = redisCache.getCacheMap(CacheConstants.TRANSACTION_TYPE_TOTAL);
        System.out.println(resultMap.toString());
        if (!resultMap.isEmpty()) {
            return AjaxResult.success().put("data",resultMap);
        }else {
            QueryWrapper<DonationTrace> wrapper = new QueryWrapper<>();
            wrapper.select("trans_type", "COUNT(*) AS count");
            wrapper.groupBy("trans_type");
            List<Map<String, Object>> dataMap = mpDonationTraceMapper.selectMaps(wrapper);

            System.out.println(dataMap.toString());
            ArrayList<HashMap<String, Object>> indicator = new ArrayList<>();
            ArrayList<Object> radarData = new ArrayList<>();

            for (int i = 0; i < dataMap.size(); i++) {
                HashMap<String, Object> indicatorMap = new HashMap<>();
                indicatorMap.put("name",dataMap.get(i).get("trans_type"));
                indicatorMap.put("max",100);
                indicator.add(indicatorMap);
                radarData.add( dataMap.get(i).get("count"));
            }

            Map<String, Object> result = new HashMap<>();
            result.put("indicator",indicator);
            result.put("radarData",radarData);
            redisCache.setCacheMap(CacheConstants.TRANSACTION_TYPE_TOTAL,result);
            redisCache.expire(CacheConstants.TRANSACTION_TYPE_TOTAL,5,TimeUnit.MINUTES);
            return AjaxResult.success().put("data",result);
        }
    }

    @Override
    public AjaxResult selectTransactionByWeek() {
        Map<String,Object> dataMap = redisCache.getCacheMap(CacheConstants.TRANSACTION_BY_WEEK);
        System.out.println(dataMap.toString());
        if (!dataMap.isEmpty()) {
            return AjaxResult.success().put("data",dataMap);
        }
        else{
            List<TransWeekVo> transWeekVos =  mpDonationTraceMapper.selectTransactionByWeek();
            ArrayList<String> weeks = new ArrayList<>();
            ArrayList<Object> trans_amount = new ArrayList<>();
            ArrayList<Object> trans_total = new ArrayList<>();
            for (int i = 0; i < transWeekVos.size(); i++) {
                weeks.add(transWeekVos.get(i).getWeek());
                trans_amount.add(transWeekVos.get(i).getTransAmount());
                trans_total.add(transWeekVos.get(i).getTransTotal());
            }

            Map<String, Object> result = new HashMap<>();
            result.put("week",weeks);
            result.put("trans_amount",trans_amount);
            result.put("trans_total",trans_total);
            redisCache.setCacheMap(CacheConstants.TRANSACTION_BY_WEEK,result);
            redisCache.expire(CacheConstants.TRANSACTION_BY_WEEK,5,TimeUnit.MINUTES);

            return AjaxResult.success().put("data",result);
        }
    }

    @Override
    public AjaxResult selectBankTransferByWeek(Integer raiseId) {
        List<BankTransferDataView> bankTransferDataViews = mpBankTransferRecordMapper.selectBankTransferRecordByWeek(raiseId);
        System.out.println(bankTransferDataViews.toString());
        ArrayList<Integer> transaction_count = new ArrayList<>();
        ArrayList<BigInteger> transaction_amount = new ArrayList<>();

        for (BankTransferDataView bankTransferDataView : bankTransferDataViews) {
            transaction_count.add(bankTransferDataView.getTransactionCount());
            transaction_amount.add(bankTransferDataView.getTransactionAmount());
        }

        AjaxResult success = AjaxResult.success();
        success.put("transaction_count",transaction_count);
        success.put("transaction_amount",transaction_amount);
        return success;
    }

    @Override
    public AjaxResult selectUserOrderStatusByMonth(String username) {
        CharityUser charityUser = mpUserMapper.selectOne(Wrappers.lambdaQuery(CharityUser.class).eq(CharityUser::getUsername, username));
        List<OrderDataVo> orderDataVoList = mpOrgMapper.selectUserOrderStatusByMonth(charityUser.getUserAddress());


        ArrayList<String> dayList = new ArrayList<>();
        ArrayList<Integer> deliveryList = new ArrayList<>();
        ArrayList<Integer> signList = new ArrayList<>();
        ArrayList<Integer> rateList = new ArrayList<>();

        // 查询当前的所有数据
        for (int i = 0; i < orderDataVoList.size(); i++) {
            dayList.add(orderDataVoList.get(i).getCountDay());
            deliveryList.add(orderDataVoList.get(i).getCountDelivery());
            signList.add(orderDataVoList.get(i).getCountSign());
            rateList.add(orderDataVoList.get(i).getCountRate());
        }
        AjaxResult success = AjaxResult.success();
        success.put("day",dayList);
        success.put("delivery",deliveryList);
        success.put("sign",signList);
        success.put("rate",rateList);
        return success;
    }

    @Override
    public AjaxResult selectOrderProcessByLogistic(Long userId) {

        Logistic logistic = mpLogisticMapper
                .selectOne(Wrappers.lambdaQuery(Logistic.class).eq(Logistic::getId, userId));

        OrderStatusVo orderStatusVo = mpActivityOrderMapper.selectOrderProcessByLogistic(logistic.getLogAddress());

        return AjaxResult.success().put("data",orderStatusVo);
    }
}
