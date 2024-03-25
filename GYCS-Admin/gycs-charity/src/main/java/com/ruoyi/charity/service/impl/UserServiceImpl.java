package com.ruoyi.charity.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.charity.domain.bo.CharityControllerUpdateUserBalanceInputBO;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.dto.Org;
import com.ruoyi.charity.domain.dto.UserBankCard;
import com.ruoyi.charity.domain.vo.UserVo;
import com.ruoyi.charity.mapper.join.SysUserJMapper;
import com.ruoyi.charity.mapper.mp.MPOrgMapper;
import com.ruoyi.charity.mapper.mp.MPUserBankMapper;
import com.ruoyi.charity.mapper.mp.MPUserMapper;
import com.ruoyi.charity.service.UserService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.SneakyThrows;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.BeanUtils;
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
    private MPOrgMapper mpOrgMapper;


    @Autowired
    private MPUserBankMapper mpUserBankMapper;

    @Autowired
    private CharityControllerService charityControllerService;



    /**
     * 查询用户的详细信息
     *
     * @param username
     * @return 返回查询的详细信息
     */
    @Override
    public AjaxResult getUserProfile(String username) {
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
        if (transactionResponse.getReturnMessage().equals(CharityControllerService.SUCCESS)) {
            // update balance of user insert to mysql as new record
            BigInteger oldAmount = charityUser.getAmount();
            BigInteger newAmount = oldAmount.add(amount);
            charityUser.setAmount(newAmount);

            MPUserMapper.updateById(charityUser);

            return AjaxResult.success().put("msg","更新账户余额成功");
        }
        return AjaxResult.error().put("msg","更新账户余额失败");
    }

    @Override
    public AjaxResult getOrgAddress(String nickName) {
        String orgAddress = mpOrgMapper.selectOne(Wrappers.lambdaQuery(Org.class).eq(Org::getOrgName, nickName)).getOrgAddress();
        return AjaxResult.success().put("localAddress",orgAddress);
    }

    @Override
    public AjaxResult userBindBankCard(UserBankCard userBankCard) {
        // 检查是否已经绑定过
        UserBankCard result = selectOneUserBankCardResult(userBankCard.getUserId());

        if (result != null) {
            return AjaxResult.error().put("msg","无需重复绑定");
        }
        int insert = mpUserBankMapper.insert(userBankCard);
        return insert > 0 ? AjaxResult.success().put("msg","绑定成功") : AjaxResult.error().put("msg","绑定失败");
    }

    private UserBankCard selectOneUserBankCardResult(Long userBankCard) {
        UserBankCard result = mpUserBankMapper.selectOne(
                Wrappers.lambdaQuery(UserBankCard.class)
                        .eq(UserBankCard::getUserId, userBankCard));
        return result;
    }

    @Override
    public AjaxResult selectUserBindBankInfo(Long userId) {
        UserBankCard result = selectOneUserBankCardResult(userId);
        if (result != null) {
            return AjaxResult.success().put("data",result);
        }
        return AjaxResult.error();
    }

    @Override
    public AjaxResult getUserAddress(Long userId) {
        CharityUser charityUser = MPUserMapper.selectOne(Wrappers.lambdaQuery(CharityUser.class).eq(CharityUser::getId, userId));
        return AjaxResult.success().put("userAddress",charityUser.getUserAddress());
    }

    @Override
    public AjaxResult updateUserProfileByBlockChain(UserVo userVo) {
        CharityUser charityUser = MPUserMapper.selectOne(Wrappers
                .lambdaQuery(CharityUser.class)
                .eq(CharityUser::getUsername, userVo.getUserName()));
        // 更新用户的基本信息
        charityUser.setCardId(userVo.getCardId());
        charityUser.setDesignation(userVo.getNickName());

        // 调用区块链更新区块链账户信息
        return AjaxResult.success().put("msg","更新用户信息成功");
    }

}
