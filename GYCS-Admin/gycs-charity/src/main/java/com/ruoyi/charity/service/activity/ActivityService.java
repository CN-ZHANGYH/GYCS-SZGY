package com.ruoyi.charity.service.activity;

import com.ruoyi.charity.domain.vo.ActiviteTraceVo;
import com.ruoyi.charity.domain.vo.ActivityInfoVo;
import com.ruoyi.common.core.domain.AjaxResult;

import java.math.BigInteger;
import java.util.List;

public interface ActivityService {
    AjaxResult initiate(ActivityInfoVo activityInfoVo, Long userId);

    List<ActivityInfoVo> selectActivityList(Long userId);

    AjaxResult feedBackActivity(BigInteger activityId, BigInteger score, String username);

    List<ActiviteTraceVo> getActivityTrace(BigInteger activityId);

}
