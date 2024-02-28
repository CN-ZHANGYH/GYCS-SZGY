package com.ruoyi.charity.service.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.vo.UserVo;
import com.ruoyi.charity.mapper.join.SysUserJMapper;
import com.ruoyi.charity.service.UserService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private SysUserJMapper sysUserJMapper;

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
}
