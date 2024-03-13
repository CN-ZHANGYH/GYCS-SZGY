package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.domain.vo.RegisterVo;

import com.ruoyi.charity.domain.vo.UserKey;
import com.ruoyi.charity.service.ICharityUserService;
import com.ruoyi.charity.service.UserKeyService;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ICharityRegister;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * @author zyh
 * @date 2024/2/26 9:05
 * @desc IntelliJ IDEA
 */

@Service
public class ICharityRegisterImpl implements ICharityRegister {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private UserKeyService userKeyService;

    @Autowired
    private RedisCache redisCache;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ICharityUserService charityUserService;

    /**
     * 用户注册功能
     * @param registerVo
     * @return 返回result
     */
    @Override
    public AjaxResult register(RegisterVo registerVo) {
        // 获取用户名和密码以及验证码
        String username = registerVo.getUsername();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        // 查询当前的用户是否已经被注册
        SysUser isRegisterUser = userService.selectUserByUserName(username);
        if (isRegisterUser != null) return AjaxResult.error().put("msg", "用户已经存在");

        //获取验证码
//        boolean captchaEnabled = configService.selectCaptchaEnabled();
//        if (captchaEnabled)
//        {
//            validateCaptcha(username, code, registerVo.getUuid());
//        }

        // 注册为系统用户
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setNickName(username);
        sysUser.setPassword(SecurityUtils.encryptPassword(password));

        // 手动添加普通用户的权限
        Long[] postIds = new Long[1];
        postIds[0] = Long.valueOf(7);
        Long[] roleIds = new Long[1];
        roleIds[0] = Long.valueOf(2);
        sysUser.setPostIds(postIds);
        sysUser.setRoleIds(roleIds);
        int status = userService.insertUser(sysUser);

        // 注册为普通用户成功之后
        if (status > 0) {
            // 这里进行调用当前的区块链注册
            UserKey blockChainUser = userKeyService.createBlockChainUser();
            CharityUser charityUser = new CharityUser();
            charityUser.setId(sysUser.getUserId());
            charityUser.setCardId(sysUser.getUserName());
            charityUser.setUsername(sysUser.getUserName());
            charityUser.setAmount(BigInteger.valueOf(0));
            charityUser.setCredit(0);
            charityUser.setVoteCount(0);
            charityUser.setWithdrawCount(0);
            charityUser.setUserAddress(blockChainUser.getUserAddress());
            charityUser.setPrivateKey(blockChainUser.getPrivateKey());
            charityUser.setPublicKey(blockChainUser.getPublicKey());
            charityUser.setDesignation(sysUser.getUserName());
            charityUserService.insertCharityUser(charityUser);

            // 封装rabbitmq的消息 通过rabbitmq实现消费者收到消息进行消费实现当前的用户是异步注册用户上链
            MessageResult messageResult = new MessageResult();
            messageResult.setMessage("注册成功");
            messageResult.setData(JSONObject.toJSONString(sysUser));
            messageResult.setCode(HttpStatus.SUCCESS);

            // 封装当前的消息实体类为json格式数据
            String message = JSONObject.toJSONString(messageResult);

            // 发送消息
            rabbitTemplate.convertAndSend("direct_register_exchange","REGISTER",message);

            // 注册成功
            AjaxResult result = AjaxResult.success();
            result.put("msg","注册成功");
            result.put("user",sysUser);
            return result;
        }
        // 注册失败
        AjaxResult result = AjaxResult.error();
        result.put("msg","注册失败");
        return result;

    }


    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }
}
