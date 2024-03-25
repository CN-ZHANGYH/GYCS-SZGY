package com.ruoyi.charity.service;

import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.dto.UserBankCard;
import com.ruoyi.charity.domain.vo.UserVo;
import com.ruoyi.common.core.domain.AjaxResult;

import java.math.BigInteger;

public interface UserService {
    AjaxResult getUserProfile(String username);

    AjaxResult updateBalance(String username, BigInteger amount);

    AjaxResult getOrgAddress(String nickName);

    AjaxResult userBindBankCard(UserBankCard userBankCard);

    AjaxResult selectUserBindBankInfo(Long userId);

    AjaxResult getUserAddress(Long userId);

    AjaxResult updateUserProfileByBlockChain(UserVo userVo);

}
