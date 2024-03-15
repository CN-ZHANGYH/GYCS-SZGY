package com.ruoyi.charity.listener;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.charity.domain.dto.ActivityArticle;
import com.ruoyi.charity.domain.dto.CharityActivitieInfo;
import com.ruoyi.charity.domain.vo.ActivityInfoVo;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.mapper.mp.MPActivityArticleMapper;
import com.ruoyi.charity.mapper.mp.MPActivityMapper;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.bean.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zyh
 * @date 2024/3/15 8:58
 * @desc IntelliJ IDEA
 */

@Slf4j
@Component
// bindings其实就是用来确定队列和交换机绑定关系
@RabbitListener(bindings =@QueueBinding(
        value = @Queue(value = "activity.direct.queue",autoDelete = "false"),
        exchange = @Exchange(value = "direct_activity_init_exchange", type = ExchangeTypes.DIRECT),
        key = {"ACTIVITY_INIT_KEY"}
))
public class ActivityInitDirectListener {


    @Autowired
    private RedisCache redisCache;


    @Autowired
    private MPActivityMapper MPActivityMapper;

    @Autowired
    private MPActivityArticleMapper MPActivityArticleMapper;


    @RabbitHandler
    public void process(String message) {
        // 接收key为register的订阅模式交换机发来的消息进行消费
        MessageResult messageResult = JSONObject.parseObject(message, MessageResult.class);
        log.info("DirectReceiver消费者收到消息  : {}" + messageResult.getMessage());

        ActivityInfoVo activityInfoVo = JSONObject.parseObject(messageResult.getData(), ActivityInfoVo.class);

        // 查询当前公益活动记录的ID
        Long activityId = MPActivityMapper
                .selectOne(Wrappers.lambdaQuery(CharityActivitieInfo.class).eq(CharityActivitieInfo::getTitle, activityInfoVo.getTitle()))
                .getId();
        // 使用BeanUtils工具类复制对象
        ActivityArticle activityArticle = new ActivityArticle();
        BeanUtils.copyProperties(activityInfoVo,activityArticle);
        activityArticle.setActivityId(activityId);
        MPActivityArticleMapper.insert(activityArticle);

        // 一旦更新 删除Redis缓存中的数据
        redisCache.deleteObject(CacheConstants.ACTIVITY_LIST_KEY);
    }
}
