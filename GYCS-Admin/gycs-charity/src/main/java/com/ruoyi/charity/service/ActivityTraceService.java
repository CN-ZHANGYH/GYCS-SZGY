package com.ruoyi.charity.service;

import com.ruoyi.charity.domain.vo.MaterialInfoVo;
import com.ruoyi.common.core.domain.AjaxResult;

public interface ActivityTraceService {
    AjaxResult donation(MaterialInfoVo materialInfoVo,String username);


    AjaxResult selectUserMaterialOfRelationship(Integer activityId,String nickName);
}
