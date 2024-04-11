package com.ruoyi.common.core.domain.model;

/**
 * 用户登录对象
 * 
 * @author ruoyi
 */
public class LoginBody
{
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;


    private String address;
    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

    public LoginBody() {
    }

    public LoginBody(String username, String password, String address, String code, String uuid) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.code = code;
        this.uuid = uuid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "LoginBody{username = " + username + ", password = " + password + ", address = " + address + ", code = " + code + ", uuid = " + uuid + "}";
    }
}
