package com.ruoyi.charity.service;

import com.ruoyi.charity.domain.dto.CharityOrder;
import com.ruoyi.charity.domain.vo.OrderMessage;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

public interface OrderService {
    AjaxResult selectOrderIsNotSignList(Long userId, Integer pageNum, Integer pageSize);

    AjaxResult updateOrderMessage(OrderMessage orderMessage);

    AjaxResult endOrderById(Integer orderId);
}
