package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.domain.vo.RegisterVo;

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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;


    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        String msg = "";

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

        Long[] postIds = new Long[1];
        postIds[0] = Long.valueOf(7);
        Long[] roleIds = new Long[1];
        roleIds[0] = Long.valueOf(2);
        sysUser.setPostIds(postIds);
        sysUser.setRoleIds(roleIds);
        userService.insertUser(sysUser);

        // 封装rabbitmq的消息
        MessageResult messageResult = new MessageResult();
        messageResult.setMessage("注册成功");
        messageResult.setData(JSONObject.toJSONString(sysUser));
        messageResult.setCode(HttpStatus.SUCCESS);

        String message = JSONObject.toJSONString(messageResult);
        // 注册为区块链用户
        rabbitTemplate.convertAndSend("REGISTER_EXCHANGE","REGISTER",message);


        return null;
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
