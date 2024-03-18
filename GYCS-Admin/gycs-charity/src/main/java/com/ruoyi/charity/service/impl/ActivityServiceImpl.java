package com.ruoyi.charity.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.charity.domain.bo.CharityControllerInitiateWelfareActivitieInputBO;
import com.ruoyi.charity.domain.bo.CharityControllerScoringOfActiviteInputBO;
import com.ruoyi.charity.domain.dto.ActiviteTrace;
import com.ruoyi.charity.domain.dto.ActiviteTransaction;
import com.ruoyi.charity.domain.dto.ActivityArticle;
import com.ruoyi.charity.domain.dto.CharityActivitieInfo;
import com.ruoyi.charity.domain.vo.ActiviteTraceVo;
import com.ruoyi.charity.domain.vo.ActivityInfoVo;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.mapper.join.ActiviteTraceJMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private ActiviteTraceJMapper activiteTraceJMapper;

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

        CharityControllerInitiateWelfareActivitieInputBO activitieInputBO = new CharityControllerInitiateWelfareActivitieInputBO();
        activitieInputBO.set_title(charityActivitieInfo.getTitle());
        activitieInputBO.set_startTime(BigInteger.valueOf(BlockTimeUtil.stringToMillis(charityActivitieInfo.getStartTime(),"yyyy-MM-dd")));
        activitieInputBO.set_endTime(BigInteger.valueOf(BlockTimeUtil.stringToMillis(charityActivitieInfo.getEndTime(),"yyyy-MM-dd")));
        activitieInputBO.set_logisticAddress(charityActivitieInfo.getLogisticAddress());
        activitieInputBO.set_lncomeAddress(charityActivitieInfo.getLncomeAddress());
        activitieInputBO.set_logisticType(charityActivitieInfo.getLogisticType());

        TransactionResponse transactionResponse = charityControllerService.InitiateWelfareActivitie(activitieInputBO);
        if (transactionResponse.getReturnMessage().equals(CharityControllerService.SUCCESS)) {

            // 获取返回的值
            JSONArray result = JSONArray.parseArray(
                    JSONArray.parseArray(transactionResponse.getValues())
                            .get(0)
                            .toString());

            // [8,"儿子康废了",1710647695588,1710345600000,1710604800000,"顺丰快递","0x38347583f46ddc7859decaeaf729016ff8539d59","0xe061c65c1f24440e8e790c604df99bd2ddbe6a7b",1,0,0]
            charityActivitieInfo.setId(result.getLongValue(0));
            charityActivitieInfo.setTotalPeople(result.getLongValue(9));
            charityActivitieInfo.setTotalMaterias(result.getLongValue(10));
            charityActivitieInfo.setStatus(BigInteger.valueOf(1));
            MPActivityMapper.insert(charityActivitieInfo);

            // 这里是链上交易成功， 但是需要更新一张新的表，就是附带的文章表，所以这里需要直接进行Rabbitmq消息队列 通知异步操作
            // 使用rabbitmq消息队列进行异步处理 优化当前的接口耗时
            MessageResult messageResult = new MessageResult();
            messageResult.setCode(HttpStatus.SUCCESS);
            messageResult.setMessage("用户发起了公益活动");
            messageResult.setData(JSONObject.toJSONString(activityInfoVo));
            rabbitTemplate.convertAndSend("direct_activity_init_exchange", "ACTIVITY_INIT_KEY", JSONObject.toJSONString(messageResult));

            return AjaxResult.success().put("msg", "发布成功");
        }
        return AjaxResult.error().put("msg", "发布失败");
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

    @Override
    public AjaxResult feedBackActivity(BigInteger activityId, BigInteger score, String username) {
        // 查询当前的用户是否已经反馈过



        CharityControllerScoringOfActiviteInputBO scoreInput = new CharityControllerScoringOfActiviteInputBO();
        scoreInput.set_activitieId(activityId);
        scoreInput.set_score(score);
        try
        {
            TransactionResponse transactionResponse = charityControllerService.scoringOfActivite(scoreInput);
            if (transactionResponse.getReturnMessage().equals("Success")) {
                return AjaxResult.success().put("msg","反馈成功");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error().put("msg","反馈失败");
    }


    /**
     * 查询公益灾区活动的所有溯源信息
     * @param activityId
     * @return AjaxResult
     */
    @Override
    public List<ActiviteTraceVo> getActivityTrace(BigInteger activityId) {
        // 使用多表联查的方式查询当前的公益灾区活动对应的所有溯源信息

        MPJLambdaWrapper<ActiviteTrace> lambdaWrapper = new MPJLambdaWrapper<ActiviteTrace>()
                .eq(ActiviteTrace::getActivitId, activityId)
                .selectAll(ActiviteTrace.class)
                .select(ActiviteTransaction::getTransactionHash, ActiviteTransaction::getBlockNumber, ActiviteTransaction::getActiviteId)
                .leftJoin(ActiviteTransaction.class, ActiviteTransaction::getActiviteId, ActiviteTrace::getCharityId);

        List<ActiviteTraceVo> activiteTraceVos = activiteTraceJMapper.selectJoinList(ActiviteTraceVo.class, lambdaWrapper);
        return activiteTraceVos;
    }
}
