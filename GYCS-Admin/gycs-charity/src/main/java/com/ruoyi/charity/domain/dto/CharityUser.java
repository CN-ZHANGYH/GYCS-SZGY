package com.ruoyi.charity.domain.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigInteger;

/**
 * 用户对象 charity_user
 * 
 * @author zyh
 * @date 2024-02-27
 */

public class CharityUser
{
    private static final long serialVersionUID = 1L;

    /** 用户的ID */
    private Long id;

    /** 用户的名称 */
    @Excel(name = "用户的名称")
    private String username;

    /** 用户的余额 */
    @Excel(name = "用户的余额")
    private BigInteger amount;

    /** 用户积分 */
    @Excel(name = "用户积分")
    private Integer credit;

    /** 身份证ID */
    @Excel(name = "身份证ID")
    private String cardId;

    /** 用户称号 */
    @Excel(name = "用户称号")
    private String designation;

    /** 参与投票的次数 */
    @Excel(name = "参与投票的次数")
    private Integer voteCount;

    /** 提现的次数 */
    @Excel(name = "提现的次数")
    private Integer withdrawCount;

    /** 用户的地址 */
    @Excel(name = "用户的地址")
    private String userAddress;

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
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setAmount(BigInteger amount)
    {
        this.amount = amount;
    }

    public BigInteger getAmount()
    {
        return amount;
    }
    public void setCredit(Integer credit) 
    {
        this.credit = credit;
    }

    public Integer getCredit() 
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
    public void setVoteCount(Integer voteCount) 
    {
        this.voteCount = voteCount;
    }

    public Integer getVoteCount() 
    {
        return voteCount;
    }
    public void setWithdrawCount(Integer withdrawCount) 
    {
        this.withdrawCount = withdrawCount;
    }

    public Integer getWithdrawCount() 
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
            .append("voteCount", getVoteCount())
            .append("withdrawCount", getWithdrawCount())
            .append("userAddress", getUserAddress())
            .append("privateKey", getPrivateKey())
            .append("publicKey", getPublicKey())
            .toString();
    }
}
