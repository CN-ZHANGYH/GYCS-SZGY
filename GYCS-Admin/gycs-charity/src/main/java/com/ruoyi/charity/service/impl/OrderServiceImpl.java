package com.ruoyi.charity.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.charity.domain.dto.CharityOrder;
import com.ruoyi.charity.domain.dto.Logistic;
import com.ruoyi.charity.domain.vo.OrderMessage;
import com.ruoyi.charity.mapper.mp.MPActivityOrderMapper;
import com.ruoyi.charity.mapper.mp.MPLogisticMapper;
import com.ruoyi.charity.service.OrderService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {



    @Autowired
    private MPActivityOrderMapper mpActivityOrderMapper;


    @Autowired
    private MPLogisticMapper mpLogisticMapper;


    @Override
    public AjaxResult selectOrderIsNotSignList(Long userId, Integer pageNum, Integer pageSize) {
        // 查询当前的物流公司的地址
        Logistic logistic = mpLogisticMapper
                .selectOne(Wrappers.lambdaQuery(Logistic.class).eq(Logistic::getId, userId));


        Page<CharityOrder> page = new Page<>(pageNum, pageSize);

        Page<CharityOrder> charityOrderPage = mpActivityOrderMapper
                .selectPage(page, Wrappers.lambdaQuery(CharityOrder.class)
                        .eq(CharityOrder::getLogisticAddress, logistic.getLogAddress())
                        .eq(CharityOrder::getIsSign, false));

        List<CharityOrder> charityOrderList = charityOrderPage.getRecords();

        AjaxResult success = AjaxResult.success();
        success.put("total",charityOrderPage.getTotal());
        success.put("rows",charityOrderList);
        return success;
    }

    @Override
    public AjaxResult updateOrderMessage(OrderMessage orderMessage) {
        CharityOrder charityOrder = mpActivityOrderMapper.selectOne(Wrappers.lambdaQuery(CharityOrder.class).eq(CharityOrder::getId, orderMessage.getOrderId()));

        charityOrder.setDeliveryMessage(orderMessage.getDeliveryMessage());
        charityOrder.setSignMessage(orderMessage.getSignMessage());
        charityOrder.setEndMessage(orderMessage.getEndMessage());

        mpActivityOrderMapper.updateById(charityOrder);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult endOrderById(Integer orderId) {
        CharityOrder charityOrder = mpActivityOrderMapper
                .selectOne(Wrappers.lambdaQuery(CharityOrder.class).eq(CharityOrder::getId, orderId));

        if (charityOrder.getDeliveryMessage().isEmpty() ||
                charityOrder.getSignMessage().isEmpty() || charityOrder.getEndMessage().isEmpty()) {
            return AjaxResult.error().put("msg","物流未完成");
        }
        charityOrder.setIsSign(true);
        mpActivityOrderMapper.updateById(charityOrder);
        return AjaxResult.success().put("msg","订单完成");
    }
}
