package com.ruoyi.charity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.charity.domain.bo.CharityControllerInitiateWelfareActivitieInputBO;
import com.ruoyi.charity.domain.dto.ActivityArticle;
import com.ruoyi.charity.domain.dto.CharityActivitieInfo;
import com.ruoyi.charity.domain.vo.ActivityInfoVo;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.mapper.join.ActivityJMapper;
import com.ruoyi.charity.mapper.join.ArticleJMapper;
import com.ruoyi.charity.mapper.mp.MPActivityArticleMapper;
import com.ruoyi.charity.mapper.mp.MPActivityMapper;
import com.ruoyi.charity.service.ActivityService;
import com.ruoyi.charity.utils.BlockTimeUtil;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import lombok.SneakyThrows;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class ActivityServiceImpl implements ActivityService {


    @Autowired
    private MPActivityMapper MPActivityMapper;

    @Autowired
    private MPActivityArticleMapper MPActivityArticleMapper;

    @Autowired
    private CharityControllerService charityControllerService;

    @Autowired
    private ActivityJMapper activityJMapper;

    @Autowired
    private ArticleJMapper articleJMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        BeanUtils.copyProperties(activityInfoVo,charityActivitieInfo);

        int status = MPActivityMapper.insert(charityActivitieInfo);

        if (status > 0) {
            CharityControllerInitiateWelfareActivitieInputBO activitieInputBO = new CharityControllerInitiateWelfareActivitieInputBO();
            activitieInputBO.set_title(charityActivitieInfo.getTitle());
            activitieInputBO.set_startTime(BigInteger.valueOf(BlockTimeUtil.stringToMillis(charityActivitieInfo.getStartTime(),"yyyy-MM-dd")));
            activitieInputBO.set_endTime(BigInteger.valueOf(BlockTimeUtil.stringToMillis(charityActivitieInfo.getEndTime(),"yyyy-MM-dd")));
            activitieInputBO.set_logisticAddress(charityActivitieInfo.getLogisticAddress());
            activitieInputBO.set_lncomeAddress(charityActivitieInfo.getLncomeAddress());
            activitieInputBO.set_logisticType(charityActivitieInfo.getLogisticType());

            TransactionResponse transactionResponse = charityControllerService.InitiateWelfareActivitie(activitieInputBO);
            if (transactionResponse.getReturnMessage().equals(CharityControllerService.SUCCESS)) {
                // 这里是链上交易成功， 但是需要更新一张新的表，就是附带的文章表，所以这里需要直接进行Rabbitmq消息队列 通知异步操作
                // 使用rabbitmq消息队列进行异步处理 优化当前的接口耗时
                MessageResult messageResult = new MessageResult();
                messageResult.setCode(HttpStatus.SUCCESS);
                messageResult.setMessage("用户发起了公益活动");
                messageResult.setData(JSONObject.toJSONString(activityInfoVo));
                rabbitTemplate.convertAndSend("direct_activity_init_exchange","ACTIVITY_INIT_KEY",JSONObject.toJSONString(messageResult));

                return AjaxResult.success().put("msg","发布成功");
            }
        }
        return AjaxResult.error().put("msg","发布失败");
    }

    @Override
    public AjaxResult selectActivityList() {
        // 直接先从Redis中读取数据 如果没有则继续从Mysql重新读取
        List<ActivityInfoVo> activityInfoVoList = redisCache.getCacheObject(CacheConstants.ACTIVITY_LIST_KEY);

        if (activityInfoVoList == null) {
            MPJLambdaWrapper<CharityActivitieInfo> lambdaWrapper = new MPJLambdaWrapper<CharityActivitieInfo>()
                    .selectAll(CharityActivitieInfo.class)
                    .selectAll(ActivityArticle.class)
                    .leftJoin(ActivityArticle.class, ActivityArticle::getActivityId, CharityActivitieInfo::getId);

            List<ActivityInfoVo> activityInfoVos = activityJMapper.selectJoinList(ActivityInfoVo.class, lambdaWrapper);

            // 直接存储Redis
            redisCache.setCacheObject(CacheConstants.ACTIVITY_LIST_KEY,activityInfoVos,5, TimeUnit.MINUTES);
            AjaxResult success = AjaxResult.success();
            success.put("msg","查询成功");
            success.put("data",activityInfoVos);
            return success;
        }

        AjaxResult success = AjaxResult.success();
        success.put("msg","查询成功");
        success.put("data",activityInfoVoList);
        return success;
    }
}
