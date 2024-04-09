package com.ruoyi.web.controller.charity.front;

import com.ruoyi.charity.domain.vo.MaterialInfoVo;
import com.ruoyi.charity.service.trace.ActivityTraceService;
import com.ruoyi.charity.service.account.LogisticService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zyh
 * @date 2024/3/15 9:20
 * @desc IntelliJ IDEA
 */

@RestController
@RequestMapping("/material")
public class MaterialFlowController {

    @Autowired
    private LogisticService logisticService;

    @Autowired
    private ActivityTraceService activityTraceService;

    @GetMapping("/address_list")
    public AjaxResult selectLogisticAddressList() {
        return logisticService.selectLogisticAddressList();
    }

    @PostMapping("/donation")
    public AjaxResult donation(@Valid @RequestBody MaterialInfoVo materialInfoVo){
        String username = SecurityUtils.getLoginUser().getUsername();
        return activityTraceService.donation(materialInfoVo,username);
    }
}
