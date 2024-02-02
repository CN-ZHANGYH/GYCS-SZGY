package com.ruoyi.charity.domain.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公益募资活动对象 charity_raise_fund
 *
 * @author zyh
 * @date 2024-02-01
 */
public class CharityRaiseFund extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公益慈善ID */
    private Long id;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String title;

    /** 活动描述 */
    @Excel(name = "活动描述")
    private String desc;

    /** 活动组织者 */
    @Excel(name = "活动组织者")
    private String promoterAddress;

    /** 活动状态 */
    @Excel(name = "活动状态")
    private Long status;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 总参与人数 */
    @Excel(name = "总参与人数")
    private Long totalPeople;

    /** 总需金额 */
    @Excel(name = "总需金额")
    private Long totalAmount;

    /** 已完成金额 */
    @Excel(name = "已完成金额")
    private Long overAmount;

    /** 已取出金额 */
    @Excel(name = "已取出金额")
    private Long withdrawAmount;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getDesc()
    {
        return desc;
    }
    public void setPromoterAddress(String promoterAddress)
    {
        this.promoterAddress = promoterAddress;
    }

    public String getPromoterAddress()
    {
        return promoterAddress;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setTotalPeople(Long totalPeople)
    {
        this.totalPeople = totalPeople;
    }

    public Long getTotalPeople()
    {
        return totalPeople;
    }
    public void setTotalAmount(Long totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public Long getTotalAmount()
    {
        return totalAmount;
    }
    public void setOverAmount(Long overAmount)
    {
        this.overAmount = overAmount;
    }

    public Long getOverAmount()
    {
        return overAmount;
    }
    public void setWithdrawAmount(Long withdrawAmount)
    {
        this.withdrawAmount = withdrawAmount;
    }

    public Long getWithdrawAmount()
    {
        return withdrawAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("desc", getDesc())
            .append("createTime", getCreateTime())
            .append("promoterAddress", getPromoterAddress())
            .append("status", getStatus())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("totalPeople", getTotalPeople())
            .append("totalAmount", getTotalAmount())
            .append("overAmount", getOverAmount())
            .append("withdrawAmount", getWithdrawAmount())
            .toString();
    }
}
