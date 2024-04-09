package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.service.trace.ActivityTraceService;
import com.ruoyi.charity.service.trace.TraceService;
import com.ruoyi.charity.service.account.UserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/record/donation")
public class RecordController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityTraceService activityTraceService;

    @Autowired
    private TraceService traceService;

    @GetMapping("/raise_fund/list")
    public AjaxResult selectUserDonationRaiseFundRecord(){
        Long userId = SecurityUtils.getLoginUser().getUserId();
        return userService.selectUserDonationRaiseFundRecord(userId);
    }

    @GetMapping("/material/list")
    public AjaxResult selectUserDonationMaterialRecord(){
        Long userId = SecurityUtils.getLoginUser().getUserId();
        return userService.selectUserDonationMaterialRecord(userId);
    }


    @GetMapping("/raise_fund/transaction_hash")
    public AjaxResult selectTransactionHashAndBlockNumber(@RequestParam("raiseId") Integer raiseId){
        return userService.selectTransactionHashAndBlockNumber(raiseId);
    }



    @GetMapping("/material/relationship")
    public AjaxResult selectUserMaterialOfRelationship(@RequestParam("activityId") Integer activityId){
        String nickName = SecurityUtils.getLoginUser().getUser().getNickName();
        return activityTraceService.selectUserMaterialOfRelationship(activityId,nickName);
    }


    @GetMapping("/material/transaction_hash")
    public AjaxResult selectTransactionHashAndBlockNumberByMaterialId(@RequestParam("activityId") Integer activityId){
        return userService.selectTransactionHashAndBlockNumberByMaterialId(activityId);
    }


        @GetMapping("/raise_fund/latest_trans")
    public AjaxResult selectUserLatestDonationTransaction(){
        String username = SecurityUtils.getLoginUser().getUsername();
        return traceService.selectUserLatestDonationTransaction(username);
    }
}
