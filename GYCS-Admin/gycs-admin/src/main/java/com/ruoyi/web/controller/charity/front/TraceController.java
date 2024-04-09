package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.service.trace.TraceService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trace")
public class TraceController {


    @Autowired
    private TraceService traceService;


    @PostMapping("/raise_fund_material")
    public AjaxResult selectUserRaiseFundAndMaterialTraceByCardId(@RequestParam("cardId") String cardId){
        if (cardId.isEmpty()) return AjaxResult.error().put("msg","身份证号不能为空");
        return traceService.selectUserRaiseFundAndMaterialTraceByCardId(cardId);
    }


    @PostMapping("/raise_fund_detail")
    public AjaxResult selectRaiseFundDetailByRaiseId(@RequestParam("raiseId") Integer raiseId) {
        return traceService.selectRaiseFundDetailByRaiseId(raiseId);
    }


    @PostMapping("/material_detail")
    public AjaxResult selectMaterialDetailByActivityId(@RequestParam("activityId") Integer activityId) {
        return traceService.selectMaterialDetailByActivityId(activityId);
    }

}
