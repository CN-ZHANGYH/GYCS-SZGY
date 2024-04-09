package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.domain.vo.ActiviteTraceVo;
import com.ruoyi.charity.domain.vo.ActivityInfoVo;
import com.ruoyi.charity.service.activity.ActivityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController {


    @Autowired
    private ActivityService activityService;


    @PostMapping("/initiate")
    public AjaxResult initiate(@Valid @RequestBody ActivityInfoVo activityInfoVo) {
        return activityService.initiate(
                activityInfoVo,
                SecurityUtils.getUserId()
        );
    }


    @PostMapping("/feedback")
    public AjaxResult feedBackActivity(
            @RequestParam("activity_id")BigInteger activity_id,
            @RequestParam("score") BigInteger score
    ) {
        return activityService.feedBackActivity(activity_id,score,SecurityUtils.getLoginUser().getUsername());
    }


    @GetMapping("/trace")
    public TableDataInfo getActivityTrace(@RequestParam("activity_id") BigInteger activity_id) {
        startPage();
        List<ActiviteTraceVo> activityTrace = activityService.getActivityTrace(activity_id);
        return getDataTable(activityTrace);
    }

    @GetMapping("/list")
    public AjaxResult selectActivityList() {
        List<ActivityInfoVo> activityInfoVos = activityService.selectActivityList(SecurityUtils.getUserId());
        AjaxResult success = AjaxResult.success();
        success.put("total",activityInfoVos.size());
        success.put("rows",activityInfoVos);
        return success;
    }
}
