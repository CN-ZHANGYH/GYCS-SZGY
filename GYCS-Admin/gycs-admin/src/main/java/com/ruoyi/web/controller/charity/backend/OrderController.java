package com.ruoyi.web.controller.charity.backend;

import com.ruoyi.charity.domain.dto.CharityOrder;
import com.ruoyi.charity.domain.vo.OrderMessage;
import com.ruoyi.charity.service.OrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.models.auth.In;
import org.apache.tomcat.Jar;
import org.fisco.bcos.sdk.abi.datatypes.Int;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
