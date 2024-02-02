package com.ruoyi.charity.domain.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 机构用户信息对象 charity_org
 *
 * @author zyh
 * @date 2024-01-31
 */
public class Org extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 机构ID */
    private Long id;

    /** 机构的名称 */
    @Excel(name = "机构的名称")
    private String orgName;

    /** 机构的账户余额 */
    @Excel(name = "机构的账户余额")
    private Long amount;

    /** 灾区活动发起记录ID */
    @Excel(name = "灾区活动发起记录ID")
    private String activitiesList;

    /** 机构的地址 */
    @Excel(name = "机构的地址")
    private String orgAddress;

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
    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    public String getOrgName()
    {
        return orgName;
    }
    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public Long getAmount()
    {
        return amount;
    }
    public void setActivitiesList(String activitiesList)
    {
        this.activitiesList = activitiesList;
    }

    public String getActivitiesList()
    {
        return activitiesList;
    }
    public void setOrgAddress(String orgAddress)
    {
        this.orgAddress = orgAddress;
    }

    public String getOrgAddress()
    {
        return orgAddress;
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
            .append("orgName", getOrgName())
            .append("amount", getAmount())
            .append("activitiesList", getActivitiesList())
            .append("orgAddress", getOrgAddress())
            .append("privateKey", getPrivateKey())
            .append("publicKey", getPublicKey())
            .toString();
    }
}
