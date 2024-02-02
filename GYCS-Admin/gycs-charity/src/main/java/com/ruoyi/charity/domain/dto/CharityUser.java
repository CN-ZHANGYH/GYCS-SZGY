package com.ruoyi.charity.domain.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户信息表对象 charity_user
 *
 * @author zyh
 * @date 2024-02-01
 */
public class CharityUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户的ID */
    private Long id;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String username;

    /** 用户余额 */
    @Excel(name = "用户余额")
    private Long amount;

    /** 用户积分 */
    @Excel(name = "用户积分")
    private String credit;

    /** 身份证ID */
    @Excel(name = "身份证ID")
    private String cardId;

    /** 用户称号 */
    @Excel(name = "用户称号")
    private String designation;

    /** 捐款记录ID */
    @Excel(name = "捐款记录ID")
    private String donateList;

    /** 募资记录ID */
    @Excel(name = "募资记录ID")
    private String raiseList;

    /** 公益活动捐赠记录ID */
    @Excel(name = "公益活动捐赠记录ID")
    private String welfareList;

    /** 参与投票的次数 */
    @Excel(name = "参与投票的次数")
    private Long voteCount;

    /** 提现次数 */
    @Excel(name = "提现次数")
    private Long withdrawCount;

    /** 用户地址 */
    @Excel(name = "用户地址")
    private String userAddress;

    /** 用户私钥 */
    @Excel(name = "用户私钥")
    private String privateKey;

    /** 用户公钥 */
    @Excel(name = "用户公钥")
    private String publicKey;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public Long getAmount()
    {
        return amount;
    }
    public void setCredit(String credit)
    {
        this.credit = credit;
    }

    public String getCredit()
    {
        return credit;
    }
    public void setCardId(String cardId)
    {
        this.cardId = cardId;
    }

    public String getCardId()
    {
        return cardId;
    }
    public void setDesignation(String designation)
    {
        this.designation = designation;
    }

    public String getDesignation()
    {
        return designation;
    }
    public void setDonateList(String donateList)
    {
        this.donateList = donateList;
    }

    public String getDonateList()
    {
        return donateList;
    }
    public void setRaiseList(String raiseList)
    {
        this.raiseList = raiseList;
    }

    public String getRaiseList()
    {
        return raiseList;
    }
    public void setWelfareList(String welfareList)
    {
        this.welfareList = welfareList;
    }

    public String getWelfareList()
    {
        return welfareList;
    }
    public void setVoteCount(Long voteCount)
    {
        this.voteCount = voteCount;
    }

    public Long getVoteCount()
    {
        return voteCount;
    }
    public void setWithdrawCount(Long withdrawCount)
    {
        this.withdrawCount = withdrawCount;
    }

    public Long getWithdrawCount()
    {
        return withdrawCount;
    }
    public void setUserAddress(String userAddress)
    {
        this.userAddress = userAddress;
    }

    public String getUserAddress()
    {
        return userAddress;
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
            .append("username", getUsername())
            .append("amount", getAmount())
            .append("credit", getCredit())
            .append("cardId", getCardId())
            .append("designation", getDesignation())
            .append("donateList", getDonateList())
            .append("raiseList", getRaiseList())
            .append("welfareList", getWelfareList())
            .append("voteCount", getVoteCount())
            .append("withdrawCount", getWithdrawCount())
            .append("userAddress", getUserAddress())
            .append("privateKey", getPrivateKey())
            .append("publicKey", getPublicKey())
            .toString();
    }
}
