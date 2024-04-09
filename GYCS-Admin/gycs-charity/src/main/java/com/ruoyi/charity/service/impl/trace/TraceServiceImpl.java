package com.ruoyi.charity.service.impl.trace;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.charity.domain.dto.*;
import com.ruoyi.charity.domain.vo.ActiviteTraceVo;
import com.ruoyi.charity.domain.vo.DonationTraceVo;
import com.ruoyi.charity.mapper.join.ActiviteTraceJMapper;
import com.ruoyi.charity.mapper.join.DonationTraceJMapper;
import com.ruoyi.charity.mapper.mp.MPActivityTraceMapper;
import com.ruoyi.charity.mapper.mp.MPDonationTraceMapper;
import com.ruoyi.charity.mapper.mp.MPUserMapper;
import com.ruoyi.charity.service.trace.TraceService;
import com.ruoyi.common.core.domain.AjaxResult;
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

    @Autowired
    private ActiviteTraceJMapper activiteTraceJMapper;

    @Autowired
    private DonationTraceJMapper donationTraceJMapper;


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

    @Override
    public AjaxResult selectMaterialDetailByActivityId(Integer activityId) {
        // 使用多表联查的方式查询当前的公益灾区活动对应的所有溯源信息

        MPJLambdaWrapper<ActiviteTrace> lambdaWrapper = new MPJLambdaWrapper<ActiviteTrace>()
                .eq(ActiviteTrace::getCharityId, activityId)
                .selectAll(ActiviteTrace.class)
                .select(ActiviteTransaction::getTransactionHash, ActiviteTransaction::getBlockNumber, ActiviteTransaction::getActiviteId)
                .leftJoin(ActiviteTransaction.class, ActiviteTransaction::getActiviteId, ActiviteTrace::getCharityId);

        ActiviteTraceVo activiteTraceVo = activiteTraceJMapper.selectJoinOne(ActiviteTraceVo.class, lambdaWrapper);
        return AjaxResult.success().put("data",activiteTraceVo);
    }

    @Override
    public AjaxResult selectRaiseFundDetailByRaiseId(Integer raiseId) {
        MPJLambdaWrapper<DonationTrace> lambdaWrapper = new MPJLambdaWrapper<DonationTrace>()
                .eq(DonationTrace::getDonationId, raiseId)
                .selectAll(DonationTrace.class)
                .select(DonationTransaction::getTransactionHash, DonationTransaction::getBlockNumber, DonationTransaction::getStatus)
                .leftJoin(DonationTransaction.class, DonationTransaction::getRaiseId, DonationTrace::getDonationId);

        DonationTraceVo donationTraceVo = donationTraceJMapper.selectJoinOne(DonationTraceVo.class, lambdaWrapper);
        return AjaxResult.success().put("data",donationTraceVo);
    }

    @Override
    public AjaxResult selectUserLatestDonationTransaction(String username) {

        CharityUser charityUser = mpUserMapper.selectOne(Wrappers.lambdaQuery(CharityUser.class).eq(CharityUser::getUsername, username));

        MPJLambdaWrapper<DonationTrace> lambdaWrapper = new MPJLambdaWrapper<DonationTrace>()
                .eq(DonationTrace::getDonorAddress, charityUser.getUserAddress())
                .selectAll(DonationTrace.class)
                .select(DonationTransaction::getTransactionHash, DonationTransaction::getBlockNumber, DonationTransaction::getStatus)
                .leftJoin(DonationTransaction.class, DonationTransaction::getRaiseId, DonationTrace::getDonationId)
                .orderByDesc(DonationTrace::getTransTime)
                .last("LIMIT " + 5);

        List<DonationTraceVo> donationTraceVoList = donationTraceJMapper.selectJoinList(DonationTraceVo.class, lambdaWrapper);
        return AjaxResult.success().put("data",donationTraceVoList);
    }
}
