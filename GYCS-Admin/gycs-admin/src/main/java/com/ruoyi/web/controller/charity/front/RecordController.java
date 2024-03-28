package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.service.UserService;
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

}
