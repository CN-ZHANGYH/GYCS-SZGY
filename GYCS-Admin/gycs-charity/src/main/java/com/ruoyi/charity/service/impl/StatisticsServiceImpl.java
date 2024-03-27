package com.ruoyi.charity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.charity.domain.dto.DonationTrace;
import com.ruoyi.charity.domain.vo.TransWeekVo;
import com.ruoyi.charity.mapper.mp.MPDonationTraceMapper;
import com.ruoyi.charity.service.StatisticsService;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private MPDonationTraceMapper mpDonationTraceMapper;

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
}
