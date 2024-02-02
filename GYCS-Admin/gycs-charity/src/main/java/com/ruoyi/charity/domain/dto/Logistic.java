package com.ruoyi.charity.domain.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物流商用户信息对象 charity_logistic
 *
 * @author zyh
 * @date 2024-02-01
 */
public class Logistic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物流商ID */
    private Long id;

    /** 物流商的名称 */
    @Excel(name = "物流商的名称")
    private String logName;

    /** 物流商的地址 */
    @Excel(name = "物流商的地址")
    private String logAddress;

    /** 用户的私钥 */
    @Excel(name = "用户的私钥")
    private String privateKey;

    /** 用户的公钥 */
    @Excel(name = "用户的公钥")
    private String publicKey;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setLogName(String logName)
    {
        this.logName = logName;
    }

    public String getLogName()
    {
        return logName;
    }
    public void setLogAddress(String logAddress)
    {
        this.logAddress = logAddress;
    }

    public String getLogAddress()
    {
        return logAddress;
    }
    public void setPrivateKey(String privateKey)
    {
        this.privateKey = privateKey;
    }

    public String getPrivateKey()
    {
        return privateKey;
    }
    public void setPublicKey(String publicKey)
    {
        this.publicKey = publicKey;
    }

    public String getPublicKey()
    {
        return publicKey;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("logName", getLogName())
            .append("logAddress", getLogAddress())
            .append("privateKey", getPrivateKey())
            .append("publicKey", getPublicKey())
            .toString();
    }
}
