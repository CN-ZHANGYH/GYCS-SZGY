package com.ruoyi.charity.domain.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 捐款信息记录对象 charity_donation
 *
 * @author zyh
 * @date 2024-02-01
 */
public class DonationTrace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公益溯源ID */
    private Long donationId;

    /** 捐款人地址 */
    @Excel(name = "捐款人地址")
    private String donorAddress;

    /** 捐款金额 */
    @Excel(name = "捐款金额")
    private Long amount;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transTime;

    /** 交易类型 */
    @Excel(name = "交易类型")
    private String transType;

    /** 交易状态 */
    @Excel(name = "交易状态")
    private Long isDonation;

    /** 捐款来源 */
    @Excel(name = "捐款来源")
    private String source;

    /** 捐款描述 */
    @Excel(name = "捐款描述")
    private String desc;

    /** 捐款接收方机构 */
    @Excel(name = "捐款接收方机构")
    private String destAddress;

    /** 集资ID */
    @Excel(name = "集资ID")
    private Long raiseId;

    public void setDonationId(Long donationId)
    {
        this.donationId = donationId;
    }

    public Long getDonationId()
    {
        return donationId;
    }
    public void setDonorAddress(String donorAddress)
    {
        this.donorAddress = donorAddress;
    }

    public String getDonorAddress()
    {
        return donorAddress;
    }
    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public Long getAmount()
    {
        return amount;
    }
    public void setTransTime(Date transTime)
    {
        this.transTime = transTime;
    }

    public Date getTransTime()
    {
        return transTime;
    }
    public void setTransType(String transType)
    {
        this.transType = transType;
    }

    public String getTransType()
    {
        return transType;
    }
    public void setIsDonation(Long isDonation)
    {
        this.isDonation = isDonation;
    }

    public Long getIsDonation()
    {
        return isDonation;
    }
    public void setSource(String source)
    {
        this.source = source;
    }

    public String getSource()
    {
        return source;
    }
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getDesc()
    {
        return desc;
    }
    public void setDestAddress(String destAddress)
    {
        this.destAddress = destAddress;
    }

    public String getDestAddress()
    {
        return destAddress;
    }
    public void setRaiseId(Long raiseId)
    {
        this.raiseId = raiseId;
    }

    public Long getRaiseId()
    {
        return raiseId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("donationId", getDonationId())
            .append("donorAddress", getDonorAddress())
            .append("amount", getAmount())
            .append("transTime", getTransTime())
            .append("transType", getTransType())
            .append("isDonation", getIsDonation())
            .append("source", getSource())
            .append("desc", getDesc())
            .append("destAddress", getDestAddress())
            .append("raiseId", getRaiseId())
            .toString();
    }
}
