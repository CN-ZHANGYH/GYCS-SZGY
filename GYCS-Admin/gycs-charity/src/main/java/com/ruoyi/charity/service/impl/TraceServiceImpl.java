package com.ruoyi.charity.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.charity.domain.dto.ActiviteTrace;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.dto.DonationTrace;
import com.ruoyi.charity.mapper.mp.MPActivityTraceMapper;
import com.ruoyi.charity.mapper.mp.MPDonationTraceMapper;
import com.ruoyi.charity.mapper.mp.MPUserMapper;
import com.ruoyi.charity.service.TraceService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Service
public class TraceServiceImpl implements TraceService {

    @Autowired
    private MPUserMapper mpUserMapper;

    @Autowired
    private MPActivityTraceMapper mpActivityTraceMapper;

    @Autowired
    private MPDonationTraceMapper mpDonationTraceMapper;

    @Override
    public AjaxResult selectUserRaiseFundAndMaterialTraceByCardId(String cardId) {
        // 根据身份证查询当前的用户地址信息
        CharityUser charityUser = mpUserMapper
                .selectOne(Wrappers
                        .lambdaQuery(CharityUser.class)
                        .eq(CharityUser::getCardId, cardId));

        if (Objects.isNull(charityUser)) return AjaxResult.error().put("msg","当前身份证号无效");

        // 查询当前用户的所有物资捐赠溯源信息
        List<ActiviteTrace> activiteTraceList = mpActivityTraceMapper
                .selectList(Wrappers
                        .lambdaQuery(ActiviteTrace.class)
                        .eq(ActiviteTrace::getSourceAddress, charityUser.getUserAddress()));

        // 查询当前用户的所有捐款溯源信息
        List<DonationTrace> donationTraceList = mpDonationTraceMapper.selectList(Wrappers
                .lambdaQuery(DonationTrace.class)
                .eq(DonationTrace::getDonorAddress, charityUser.getUserAddress()));

        if (activiteTraceList.isEmpty() && donationTraceList.isEmpty()) return AjaxResult.error().put("msg","暂无溯源信息");

        AjaxResult success = AjaxResult.success();
        success.put("msg","查询成功");
        HashMap<String, Object> activiteMap = new HashMap<>();
        activiteMap.put("total",activiteTraceList.size());
        activiteMap.put("rows",activiteTraceList);

        HashMap<String, Object> donationMap = new HashMap<>();
        donationMap.put("total",donationTraceList.size());
        donationMap.put("rows",donationTraceList);


        success.put("activiteTrace",activiteMap);
        success.put("donationTrace",donationMap);

        return success;


    }
}
