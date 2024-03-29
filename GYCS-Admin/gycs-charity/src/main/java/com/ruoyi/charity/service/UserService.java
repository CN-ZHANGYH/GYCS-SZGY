package com.ruoyi.charity.service;

import com.ruoyi.common.core.domain.AjaxResult;

import java.math.BigInteger;

public interface UserService {
    AjaxResult getUserProfile(UserService userService,String username);

    AjaxResult updateBalance(String username, BigInteger amount);

    AjaxResult getOrgAddress(String nickName);

}
