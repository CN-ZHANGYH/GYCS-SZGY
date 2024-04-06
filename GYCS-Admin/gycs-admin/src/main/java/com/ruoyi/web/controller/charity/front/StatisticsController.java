package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.domain.vo.TransWeekVo;
import com.ruoyi.charity.service.RaiseFundService;
import com.ruoyi.charity.service.StatisticsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/data")
public class StatisticsController extends BaseController {

    @Autowired
    private RaiseFundService raiseFundService;


    @Autowired
    private StatisticsService statisticsService;


    /**
     * 根据ID查询公益募资的各个数据统计
     * @param raiseId
     * @return AjaxResult
     */
    @GetMapping("/raise_fund/total")
    public AjaxResult selectRaiseFundTotalData(@RequestParam("raiseId")BigInteger raiseId) {
        return raiseFundService.selectRaiseFundTotalData(raiseId);
    }

    @GetMapping("/trans_type")
    public AjaxResult selectTransactionTypeTotalData() {
        return statisticsService.selectTransactionTypeTotalData();
    }

    @GetMapping("/trans_week")
    public AjaxResult selectTransactionByWeek(){
        return statisticsService.selectTransactionByWeek();
    }

    @GetMapping("/bank_transfer_week")
    public AjaxResult selectBankTransferByWeek(@RequestParam("raiseId") Integer raiseId){
        return statisticsService.selectBankTransferByWeek(raiseId);
    }

    @GetMapping("/order_status")
    public AjaxResult selectUserOrderStatusByMonth(){
        String username = SecurityUtils.getLoginUser().getUsername();
        return statisticsService.selectUserOrderStatusByMonth(username);
    }

    @GetMapping("/order_process")
    public AjaxResult selectOrderProcessByLogistic() {
        return statisticsService.selectOrderProcessByLogistic(SecurityUtils.getLoginUser().getUserId());
    }
}
