package com.ruoyi.charity.service;

import com.ruoyi.common.core.domain.AjaxResult;

public interface TraceService {
    AjaxResult selectUserRaiseFundAndMaterialTraceByCardId(String cardId);

    AjaxResult selectMaterialDetailByActivityId(Integer activityId);

    AjaxResult selectRaiseFundDetailByRaiseId(Integer raiseId);
}
