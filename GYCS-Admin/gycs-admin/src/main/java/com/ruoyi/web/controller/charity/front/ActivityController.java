package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.domain.vo.ActivityInfoVo;
import com.ruoyi.charity.service.ActivityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController {


    @Autowired
    private ActivityService activityService;


    @PostMapping("/initiate")
    public AjaxResult initiate(@Valid  @RequestBody ActivityInfoVo activityInfoVo) {
        return activityService.initiate(
                activityInfoVo,
                SecurityUtils.getUserId()
        );
    }

    @GetMapping("/list")
    public AjaxResult selectActivityList() {
        return activityService.selectActivityList();
    }

}
