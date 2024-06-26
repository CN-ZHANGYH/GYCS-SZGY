package com.ruoyi.charity.service.account;

import com.ruoyi.charity.domain.dto.UserBankCard;
import com.ruoyi.charity.domain.vo.RankUserVo;
import com.ruoyi.charity.domain.vo.UserVo;
import com.ruoyi.common.core.domain.AjaxResult;

import java.math.BigInteger;
import java.util.List;

public interface UserService {
    AjaxResult getUserProfile(String username);

    AjaxResult updateBalance(String username, BigInteger amount);

    AjaxResult getOrgAddress(String nickName);

    AjaxResult userBindBankCard(UserBankCard userBankCard);

    AjaxResult selectUserBindBankInfo(Long userId);

    AjaxResult getUserAddress(Long userId);

    AjaxResult updateUserProfileByBlockChain(UserVo userVo);

    List<RankUserVo> getRankByUserCredit();

    AjaxResult selectUserDonationRaiseFundRecord(Long userId);

    AjaxResult selectUserDonationMaterialRecord(Long userId);

    AjaxResult selectTransactionHashAndBlockNumber(Integer raiseId);

    AjaxResult selectTransactionHashAndBlockNumberByMaterialId(Integer activityId);

    AjaxResult selectUserDonationCount(String username);


}
