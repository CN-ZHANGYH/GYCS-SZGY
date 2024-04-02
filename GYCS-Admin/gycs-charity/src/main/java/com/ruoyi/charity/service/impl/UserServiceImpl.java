package com.ruoyi.charity.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.charity.domain.bo.CharityControllerUpdateUserBalanceInputBO;
import com.ruoyi.charity.domain.dto.*;
import com.ruoyi.charity.domain.vo.RankUserVo;
import com.ruoyi.charity.domain.vo.UserVo;
import com.ruoyi.charity.mapper.join.SysUserJMapper;
import com.ruoyi.charity.mapper.mp.*;
import com.ruoyi.charity.service.UserService;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import lombok.SneakyThrows;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private SysUserJMapper sysUserJMapper;


    @Autowired
    private MPUserMapper MPUserMapper;


    @Autowired
    private MPOrgMapper mpOrgMapper;

    @Autowired
    private RedisCache redisCache;


    @Autowired
    private MPUserBankMapper mpUserBankMapper;

    @Autowired
    private MPDonationTraceMapper mpDonationTraceMapper;

    @Autowired
    private MPActivityTraceMapper mpActivityTraceMapper;

    @Autowired
    private MPDonationTransactionMapper mpDonationTransactionMapper;

    @Autowired
    private MPActiviteTransactionMapper mpActiviteTransactionMapper;

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

    @Override
    public List<RankUserVo> getRankByUserCredit() {
        MPJLambdaWrapper<SysUser> lambdaWrapper = new MPJLambdaWrapper<SysUser>()
                .isNotNull(CharityUser::getId)
                .orderByDesc(CharityUser::getCredit)
                .select(
                        SysUser::getUserId,
                        SysUser::getNickName,
                        SysUser::getSex,
                        SysUser::getAvatar
                )
                .select(
                        CharityUser::getCredit,
                        CharityUser::getVoteCount,
                        CharityUser::getWithdrawCount,
                        CharityUser::getUserAddress
                )
                .leftJoin(CharityUser.class,CharityUser::getId, SysUser::getUserId);
        List<RankUserVo> rankUserVoList = sysUserJMapper.selectJoinList(RankUserVo.class, lambdaWrapper);
        return rankUserVoList;
    }

    // 根据用户的ID查询当前的用户所有的交易记录信息
    @Override
    public AjaxResult selectUserDonationRaiseFundRecord(Long userId) {
        // 默认先从redis中读取
        List<DonationTrace> donationTraceList = redisCache.getCacheObject(CacheConstants.USER_DONATION_RAISE_FUND_RECORD + userId);
        if (donationTraceList != null) {
            AjaxResult success = AjaxResult.success();
            success.put("total",donationTraceList.size());
            success.put("rows",donationTraceList);
            return success;
        }

        CharityUser charityUser = MPUserMapper.selectById(userId);
        // 根据当前的用户地址查询该用户的所有交易记录
        donationTraceList = mpDonationTraceMapper
                .selectList(Wrappers
                        .lambdaQuery(DonationTrace.class)
                        .eq(DonationTrace::getDonorAddress, charityUser.getUserAddress()));

        // 将所有的记录存储到redis缓存中
        redisCache.setCacheObject(CacheConstants.USER_DONATION_RAISE_FUND_RECORD + userId,donationTraceList,1, TimeUnit.MINUTES);

        AjaxResult success = AjaxResult.success();
        success.put("total",donationTraceList.size());
        success.put("rows",donationTraceList);
        return success;
    }

    @Override
    public AjaxResult selectUserDonationMaterialRecord(Long userId) {
        // 默认先从redis中读取
        List<ActiviteTrace> activiteTraceList = redisCache.getCacheObject(CacheConstants.USER_DONATION_MATERIAL_RECORD + userId);
        if (activiteTraceList != null) {
            AjaxResult success = AjaxResult.success();
            success.put("total",activiteTraceList.size());
            success.put("rows",activiteTraceList);
            return success;
        }

        CharityUser charityUser = MPUserMapper.selectById(userId);
        // 根据当前的用户地址查询该用户的所有交易记录
        activiteTraceList = mpActivityTraceMapper
                .selectList(Wrappers
                        .lambdaQuery(ActiviteTrace.class)
                        .eq(ActiviteTrace::getSourceAddress, charityUser.getUserAddress()));

        // 将所有的记录存储到redis缓存中
        redisCache.setCacheObject(CacheConstants.USER_DONATION_MATERIAL_RECORD + userId,activiteTraceList,1, TimeUnit.MINUTES);

        AjaxResult success = AjaxResult.success();
        success.put("total",activiteTraceList.size());
        success.put("rows",activiteTraceList);
        return success;
    }

    @Override
    public AjaxResult selectTransactionHashAndBlockNumber(Integer raiseId) {
        DonationTransaction donationTransaction = mpDonationTransactionMapper
                .selectOne(Wrappers
                        .lambdaQuery(DonationTransaction.class)
                        .eq(DonationTransaction::getRaiseId, raiseId));
        return AjaxResult.success().put("data",donationTransaction);
    }

    @Override
    public AjaxResult selectTransactionHashAndBlockNumberByMaterialId(Integer activityId) {
        ActiviteTransaction activiteTransaction = mpActiviteTransactionMapper
                .selectOne(Wrappers
                        .lambdaQuery(ActiviteTransaction.class)
                        .eq(ActiviteTransaction::getActiviteId, activityId));
        return AjaxResult.success().put("data",activiteTransaction);
    }

    @Override
    public AjaxResult selectUserDonationCount(String username) {
        CharityUser charityUser = MPUserMapper.selectOne(Wrappers.lambdaQuery(CharityUser.class).eq(CharityUser::getUsername, username));
        Integer count = mpDonationTraceMapper.selectDonationCount(charityUser.getUserAddress());
        return AjaxResult.success().put("count",count);
    }

}
