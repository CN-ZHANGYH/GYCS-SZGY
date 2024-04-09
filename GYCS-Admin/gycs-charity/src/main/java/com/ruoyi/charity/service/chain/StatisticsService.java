package com.ruoyi.charity.service.chain;

import com.ruoyi.charity.domain.vo.TransWeekVo;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

/**
 * @author zyh
 * @date 2024/3/27 11:39
 * @desc IntelliJ IDEA
 */
public interface StatisticsService {
    AjaxResult selectTransactionTypeTotalData();

    AjaxResult selectTransactionByWeek();

    AjaxResult selectBankTransferByWeek(Integer raiseId);

    AjaxResult selectUserOrderStatusByMonth(String username);

    AjaxResult selectOrderProcessByLogistic(Long userId);
}
