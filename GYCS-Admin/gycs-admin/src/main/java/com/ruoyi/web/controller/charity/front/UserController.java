package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.domain.dto.UserBankCard;
import com.ruoyi.charity.service.UserService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public AjaxResult getUserProfile() {
        String username = SecurityUtils.getLoginUser().getUsername();
        return userService.getUserProfile(username);
    }

    @PostMapping("/update_balance")
    public AjaxResult updateBalance(@RequestParam("amount") BigInteger amount) {
        String username = SecurityUtils.getLoginUser().getUsername();
        return userService.updateBalance(username,amount);
    }

    @PostMapping("/org_address")
    public AjaxResult getOrgAddress(){
        return userService.getOrgAddress(SecurityUtils.getLoginUser().getUser().getNickName());
    }

    @PostMapping("/bind_bank")
    public AjaxResult userBindBankCard(@Valid @RequestBody UserBankCard userBankCard){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        userBankCard.setUserId(loginUser.getUserId());
        return userService.userBindBankCard(userBankCard);
    }


    @PostMapping("/get_bind_bank_info")
    public AjaxResult selectUserBindBankInfo() {
        return userService.selectUserBindBankInfo(SecurityUtils.getLoginUser().getUserId());
    }

}
