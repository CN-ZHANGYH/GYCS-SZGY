package com.ruoyi.charity.service.impl.account;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.charity.domain.dto.Logistic;
import com.ruoyi.charity.mapper.mp.MPLogisticMapper;
import com.ruoyi.charity.service.account.LogisticService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zyh
 * @date 2024/3/15 9:30
 * @desc IntelliJ IDEA
 */

@Service
public class CharityLogisticServiceImpl implements LogisticService {

    @Autowired
    private MPLogisticMapper mpLogisticMapper;

    @Override
    public AjaxResult selectLogisticAddressList() {
        List<Logistic> logisticList = mpLogisticMapper.selectList(Wrappers.lambdaQuery());


        List<HashMap<String, Object>> arrayList = logisticList.stream().map(logistic -> {
            HashMap<String, Object> data = new HashMap<>();
            data.put("label", logistic.getLogName());
            data.put("value", logistic.getLogAddress());
            return data;
        }).collect(Collectors.toList());

        return AjaxResult.success().put("data",arrayList);
    }
}
