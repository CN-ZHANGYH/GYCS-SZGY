package com.ruoyi.charity.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.charity.domain.bo.CharityControllerUpdateUserBalanceInputBO;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.vo.UserVo;
import com.ruoyi.charity.mapper.join.SysUserJMapper;
import com.ruoyi.charity.mapper.mp.MPUserMapper;
import com.ruoyi.charity.service.UserService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.SneakyThrows;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private SysUserJMapper sysUserJMapper;


    @Autowired
    private MPUserMapper MPUserMapper;


    @Autowired
    private CharityControllerService charityControllerService;



    /**
     * 查询用户的详细信息
     *
     * @param userService
     * @param username
     * @return 返回查询的详细信息
     */
    @Override
    public AjaxResult getUserProfile(UserService userService, String username) {
        MPJLambdaWrapper<SysUser> lambdaWrapper = new MPJLambdaWrapper<SysUser>()
                .eq(SysUser::getUserName,username)
                .select(
                        SysUser::getUserId,
                        SysUser::getUserName,
                        SysUser::getNickName,
                        SysUser::getEmail,
                        SysUser::getPhonenumber,
                        SysUser::getSex,
                        SysUser::getAvatar,
                        SysUser::getStatus
                )
                .select(
                        CharityUser::getAmount,
                        CharityUser::getCredit,
                        CharityUser::getCardId,
                        CharityUser::getDesignation,
                        CharityUser::getVoteCount,
                        CharityUser::getWithdrawCount,
                        CharityUser::getUserAddress,
                        CharityUser::getPrivateKey,
                        CharityUser::getPublicKey
                ).leftJoin(CharityUser.class,CharityUser::getId, SysUser::getUserId);

        UserVo userVo = sysUserJMapper.selectJoinOne(UserVo.class, lambdaWrapper);
        if (Objects.isNull(userVo)) {
            return AjaxResult.error().put("msg", "用户不存在");
        }
        AjaxResult result = AjaxResult.success();
        result.put("userVo", userVo);
        result.put("msg","查询成功");
        return result;
    }

    /**
     *  update user's balance by username,can auto set amount
     */
    @SneakyThrows
    @Override
    public AjaxResult updateBalance(String username, BigInteger amount) {

        // select one charityuser info by query the username
        CharityUser charityUser = MPUserMapper
                .selectOne(Wrappers.lambdaQuery(CharityUser.class)
                        .eq(CharityUser::getUsername, username));

        // new a balanceInputBo object,call blockchain interface or api as input parameters
        CharityControllerUpdateUserBalanceInputBO balanceInputBO = new CharityControllerUpdateUserBalanceInputBO();
        balanceInputBO.set_balance(amount);
        balanceInputBO.set_userAddress(charityUser.getUserAddress());

        // use the @SneakyThrows annotate solve this mistake
        TransactionResponse transactionResponse = charityControllerService.updateUserBalance(balanceInputBO);
        if (transactionResponse.getReturnMessage().equals("Success")) {
            // update balance of user insert to mysql as new record
            BigInteger oldAmount = charityUser.getAmount();
            BigInteger newAmount = oldAmount.add(amount);
            charityUser.setAmount(newAmount);

            MPUserMapper.updateById(charityUser);

            return AjaxResult.success().put("msg","更新账户余额成功");
        }
        return AjaxResult.error().put("msg","更新账户余额失败");
    }
}
