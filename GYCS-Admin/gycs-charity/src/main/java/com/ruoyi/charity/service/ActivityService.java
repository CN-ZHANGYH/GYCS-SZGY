package com.ruoyi.charity.service;

import com.ruoyi.charity.domain.dto.CharityActivitieInfo;
import com.ruoyi.charity.domain.vo.ActiviteTraceVo;
import com.ruoyi.charity.domain.vo.ActivityInfoVo;
import com.ruoyi.common.core.domain.AjaxResult;

import java.math.BigInteger;
import java.util.List;

public interface ActivityService {
    AjaxResult initiate(ActivityInfoVo activityInfoVo, Long userId);

    AjaxResult selectActivityList();

    AjaxResult feedBackActivity(BigInteger activityId, BigInteger score, String username);

    List<ActiviteTraceVo> getActivityTrace(BigInteger activityId);

}
