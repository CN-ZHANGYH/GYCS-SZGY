package com.ruoyi.charity.domain.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公益活动溯源记录对象 charity_activite
 *
 * @author zyh
 * @date 2024-02-01
 */
public class ActiviteTrace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公益活动溯源ID */
    private Long charityId;

    /** 种类 */
    @Excel(name = "种类")
    private Long materialType;

    /** 物资的名称 */
    @Excel(name = "物资的名称")
    private String materialName;

    /** 物资的数量 */
    @Excel(name = "物资的数量")
    private Long materialCount;

    /** 源地址 */
    @Excel(name = "源地址")
    private String sourceAddress;

    /** 物流商地址 */
    @Excel(name = "物流商地址")
    private String logisticAddress;

    /** 目标地址 */
    @Excel(name = "目标地址")
    private String destAddress;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transTime;

    /** 是否签收 */
    @Excel(name = "是否签收")
    private Long isSign;

    /** 公益活动ID */
    @Excel(name = "公益活动ID")
    private Long activitId;

    /** 溯源状态 */
    @Excel(name = "溯源状态")
    private Long status;

    /** 溯源的地址 */
    @Excel(name = "溯源的地址")
    private String traceAddress;

    /** 溯源的时间 */
    @Excel(name = "溯源的时间")
    private String traceTime;

    public void setCharityId(Long charityId)
    {
        this.charityId = charityId;
    }

    public Long getCharityId()
    {
        return charityId;
    }
    public void setMaterialType(Long materialType)
    {
        this.materialType = materialType;
    }

    public Long getMaterialType()
    {
        return materialType;
    }
    public void setMaterialName(String materialName)
    {
        this.materialName = materialName;
    }

    public String getMaterialName()
    {
        return materialName;
    }
    public void setMaterialCount(Long materialCount)
    {
        this.materialCount = materialCount;
    }

    public Long getMaterialCount()
    {
        return materialCount;
    }
    public void setSourceAddress(String sourceAddress)
    {
        this.sourceAddress = sourceAddress;
    }

    public String getSourceAddress()
    {
        return sourceAddress;
    }
    public void setLogisticAddress(String logisticAddress)
    {
        this.logisticAddress = logisticAddress;
    }

    public String getLogisticAddress()
    {
        return logisticAddress;
    }
    public void setDestAddress(String destAddress)
    {
        this.destAddress = destAddress;
    }

    public String getDestAddress()
    {
        return destAddress;
    }
    public void setTransTime(Date transTime)
    {
        this.transTime = transTime;
    }

    public Date getTransTime()
    {
        return transTime;
    }
    public void setIsSign(Long isSign)
    {
        this.isSign = isSign;
    }

    public Long getIsSign()
    {
        return isSign;
    }
    public void setActivitId(Long activitId)
    {
        this.activitId = activitId;
    }

    public Long getActivitId()
    {
        return activitId;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setTraceAddress(String traceAddress)
    {
        this.traceAddress = traceAddress;
    }

    public String getTraceAddress()
    {
        return traceAddress;
    }
    public void setTraceTime(String traceTime)
    {
        this.traceTime = traceTime;
    }

    public String getTraceTime()
    {
        return traceTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("charityId", getCharityId())
            .append("materialType", getMaterialType())
            .append("materialName", getMaterialName())
            .append("materialCount", getMaterialCount())
            .append("sourceAddress", getSourceAddress())
            .append("logisticAddress", getLogisticAddress())
            .append("destAddress", getDestAddress())
            .append("transTime", getTransTime())
            .append("isSign", getIsSign())
            .append("activitId", getActivitId())
            .append("status", getStatus())
            .append("traceAddress", getTraceAddress())
            .append("traceTime", getTraceTime())
            .toString();
    }
}
