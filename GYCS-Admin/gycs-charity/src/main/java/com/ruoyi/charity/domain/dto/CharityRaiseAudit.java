package com.ruoyi.charity.domain.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 审核对象 charity_raise_audit
 *
 * @author zyh
 * @date 2024-02-28
 */
public class CharityRaiseAudit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 募资活动ID */
    @Excel(name = "募资活动ID")
    private Long raiseId;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer apply_status;

    /** 审核人 */
    @Excel(name = "审核人")
    private String username;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setRaiseId(Long raiseId)
    {
        this.raiseId = raiseId;
    }

    public Long getRaiseId()
    {
        return raiseId;
    }
    public void setApply_status(Integer apply_status)
    {
        this.apply_status = apply_status;
    }

    public Integer getApply_status()
    {
        return apply_status;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setApplyTime(Date applyTime)
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime()
    {
        return applyTime;
    }
    public void setAuditTime(Date auditTime)
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime()
    {
        return auditTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("raiseId", getRaiseId())
            .append("apply_status", getApply_status())
            .append("username", getUsername())
            .append("applyTime", getApplyTime())
            .append("auditTime", getAuditTime())
            .toString();
    }
}
