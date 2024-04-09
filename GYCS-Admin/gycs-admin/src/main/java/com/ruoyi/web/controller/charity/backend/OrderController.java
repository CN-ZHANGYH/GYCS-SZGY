package com.ruoyi.web.controller.charity.backend;

import com.ruoyi.charity.domain.vo.OrderMessage;
import com.ruoyi.charity.service.order.OrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/list")
    public AjaxResult selectOrderIsNotSignList(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        Long userId = SecurityUtils.getUserId();
        return orderService.selectOrderIsNotSignList(userId,pageNum,pageSize);
    }

    @PostMapping("/update")
    public AjaxResult updateOrderMessage(@RequestBody OrderMessage orderMessage){
        return orderService.updateOrderMessage(orderMessage);
    }


    @GetMapping("/end_order_by_id")
    public AjaxResult endOrderById(@RequestParam("orderId") Integer orderId) {
        return orderService.endOrderById(orderId);
    }
}
