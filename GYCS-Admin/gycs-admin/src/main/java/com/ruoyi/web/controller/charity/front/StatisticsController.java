package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.service.RaiseFundService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/data")
public class StatisticsController extends BaseController {

    @Autowired
    private RaiseFundService raiseFundService;

    /**
     * 根据ID查询公益募资的各个数据统计
     * @param raiseId
     * @return AjaxResult
     */
    @GetMapping("/raise_fund/total")
    public AjaxResult selectRaiseFundTotalData(@RequestParam("raiseId")BigInteger raiseId) {
        return raiseFundService.selectRaiseFundTotalData(raiseId);
    }


}
