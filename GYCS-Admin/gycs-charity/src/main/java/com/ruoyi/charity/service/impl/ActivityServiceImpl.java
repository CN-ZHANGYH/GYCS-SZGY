package com.ruoyi.charity.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.charity.domain.bo.CharityControllerInitiateWelfareActivitieInputBO;
import com.ruoyi.charity.domain.dto.ActivityArticle;
import com.ruoyi.charity.domain.dto.CharityActivitieInfo;
import com.ruoyi.charity.domain.vo.ActivityInfoVo;
import com.ruoyi.charity.mapper.mp.ActivityArticleMapper;
import com.ruoyi.charity.mapper.mp.ActivityMapper;
import com.ruoyi.charity.service.ActivityService;
import com.ruoyi.charity.utils.BlockTimeUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import lombok.SneakyThrows;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigInteger;


@Service
public class ActivityServiceImpl implements ActivityService {


    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityArticleMapper activityArticleMapper;

    @Autowired
    private CharityControllerService charityControllerService;


    /**
     * 用于发起公益活动业务
     * @param activityInfoVo
     * @param userId
     * @return AjaxResult
     */
    @SneakyThrows
    @Override
    public AjaxResult initiate(ActivityInfoVo activityInfoVo, Long userId) {
        // query the user's role by username
        CharityActivitieInfo charityActivitieInfo = new CharityActivitieInfo();
        ActivityArticle activityArticle = new ActivityArticle();
        BeanUtils.copyProperties(activityInfoVo,charityActivitieInfo);
        BeanUtils.copyProperties(activityInfoVo,activityArticle);


        int status = activityMapper.insert(charityActivitieInfo);
        if (status > 0) {
            CharityControllerInitiateWelfareActivitieInputBO activitieInputBO = new CharityControllerInitiateWelfareActivitieInputBO();
            activitieInputBO.set_title(charityActivitieInfo.getTitle());
            activitieInputBO.set_startTime(BigInteger.valueOf(BlockTimeUtil.stringToMillis(charityActivitieInfo.getStartTime(),"yyyy-MM-dd")));
            activitieInputBO.set_endTime(BigInteger.valueOf(BlockTimeUtil.stringToMillis(charityActivitieInfo.getEndTime(),"yyyy-MM-dd")));
            activitieInputBO.set_logisticAddress(charityActivitieInfo.getLogisticAddress());
            activitieInputBO.set_lncomeAddress(charityActivitieInfo.getLncomeAddress());
            activitieInputBO.set_logisticType(charityActivitieInfo.getLogisticType());

            TransactionResponse transactionResponse = charityControllerService.InitiateWelfareActivitie(activitieInputBO);
            if (transactionResponse.getReturnMessage().equals("Success")) {
                Long activityId = activityMapper
                        .selectOne(Wrappers.lambdaQuery(CharityActivitieInfo.class).eq(CharityActivitieInfo::getTitle, activityInfoVo.getTitle()))
                        .getId();
                activityArticle.setActivityId(activityId);
                activityArticleMapper.insert(activityArticle);

                return AjaxResult.success().put("msg","发布成功");
            }
        }
        return null;

    }

    @Override
    public AjaxResult selectActivityList() {
        
        return null;

    }
}
