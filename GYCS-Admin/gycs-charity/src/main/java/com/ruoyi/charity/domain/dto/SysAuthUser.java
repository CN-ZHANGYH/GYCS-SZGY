package com.ruoyi.charity.domain.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("sys_auth_user")
public class SysAuthUser {

    private static final long serialVersionUID = 1L;

    /** 授权ID */
    @TableField(value = "auth_id")
    private Long authId;

    /** 第三方平台用户唯一ID */
    @TableField(value = "uuid")
    private String uuid;

    /** 系统用户ID */
    @TableField(value = "user_id")
    private Long userId;

    /** 登录账号 */
    @TableField(value = "user_name")
    private String userName;

    /** 用户昵称 */
    @TableField(value = "nick_name")
    private String nickName;

    /** 头像地址 */
    @TableField(value = "avatar")
    private String avatar;

    /** 用户邮箱 */
    @TableField(value = "email")
    private String email;

    /** 用户来源 */
    @TableField(value = "source")
    private String source;

}
