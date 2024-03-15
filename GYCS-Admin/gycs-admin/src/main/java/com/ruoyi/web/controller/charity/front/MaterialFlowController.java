package com.ruoyi.web.controller.charity.front;

import com.ruoyi.charity.service.LogisticService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyh
 * @date 2024/3/15 9:20
 * @desc IntelliJ IDEA
 */

@RestController
@RequestMapping("/logistic")
public class MaterialFlowController {

    @Autowired
    private LogisticService logisticService;

    @GetMapping("/address_list")
    public AjaxResult selectLogisticAddressList() {
        return logisticService.selectLogisticAddressList();
    }

    @PostMapping("/donation")
    public AjaxResult donation(){
        return null;
    }
}
