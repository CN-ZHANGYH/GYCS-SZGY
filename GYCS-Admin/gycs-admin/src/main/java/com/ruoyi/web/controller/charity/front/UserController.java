package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.service.UserService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public AjaxResult getUserProfile(@RequestParam String username) {
        return userService.getUserProfile(userService,username);
    }
}
