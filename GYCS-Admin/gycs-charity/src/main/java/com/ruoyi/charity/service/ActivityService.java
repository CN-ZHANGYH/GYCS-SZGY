package com.ruoyi.charity.service;

import com.ruoyi.charity.domain.dto.CharityActivitieInfo;
import com.ruoyi.charity.domain.vo.ActivityInfoVo;
import com.ruoyi.common.core.domain.AjaxResult;

public interface ActivityService {
    AjaxResult initiate(ActivityInfoVo activityInfoVo, Long userId);

    AjaxResult selectActivityList();

}
