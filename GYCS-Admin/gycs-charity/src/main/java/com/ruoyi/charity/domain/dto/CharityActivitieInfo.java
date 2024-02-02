package com.ruoyi.charity.domain.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公益灾区捐赠活动对象 charity_activitie_info
 *
 * @author zyh
 * @date 2024-02-01
 */
public class CharityActivitieInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动ID */
    private Long id;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String title;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 物流方式 */
    @Excel(name = "物流方式")
    private String logisticType;

    /** 物流商地址 */
    @Excel(name = "物流商地址")
    private String logisticAddress;

    /** 代理人地址 */
    @Excel(name = "代理人地址")
    private String lncomeAddress;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 总参与人数 */
    @Excel(name = "总参与人数")
    private Long totalPeople;

    /** 总物资数量 */
    @Excel(name = "总物资数量")
    private Long totalMaterias;

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
    public void setLogisticType(String logisticType)
    {
        this.logisticType = logisticType;
    }

    public String getLogisticType()
    {
        return logisticType;
    }
    public void setLogisticAddress(String logisticAddress)
    {
        this.logisticAddress = logisticAddress;
    }

    public String getLogisticAddress()
    {
        return logisticAddress;
    }
    public void setLncomeAddress(String lncomeAddress)
    {
        this.lncomeAddress = lncomeAddress;
    }

    public String getLncomeAddress()
    {
        return lncomeAddress;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setTotalPeople(Long totalPeople)
    {
        this.totalPeople = totalPeople;
    }

    public Long getTotalPeople()
    {
        return totalPeople;
    }
    public void setTotalMaterias(Long totalMaterias)
    {
        this.totalMaterias = totalMaterias;
    }

    public Long getTotalMaterias()
    {
        return totalMaterias;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("createTime", getCreateTime())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("logisticType", getLogisticType())
            .append("logisticAddress", getLogisticAddress())
            .append("lncomeAddress", getLncomeAddress())
            .append("status", getStatus())
            .append("totalPeople", getTotalPeople())
            .append("totalMaterias", getTotalMaterias())
            .toString();
    }
}
