package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.service.UserService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public AjaxResult getUserProfile(@RequestParam String username) {
        return userService.getUserProfile(userService,username);
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

    @PostMapping("/user_address")
    public AjaxResult getUserAddress() {
        return userService.getUserAddress(SecurityUtils.getLoginUser().getUsername());
    }


}
