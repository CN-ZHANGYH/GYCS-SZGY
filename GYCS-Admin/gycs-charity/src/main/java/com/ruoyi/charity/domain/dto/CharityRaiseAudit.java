package com.ruoyi.charity.domain.dto;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("charity_raise_audit")
public class CharityRaiseAudit
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 募资活动ID */
    @Excel(name = "募资活动ID")
    @TableField("raise_id")
    private Long raiseId;

    /** 审核状态 */
    @Excel(name = "审核状态")
    @TableField("apply_status")
    private Integer applyStatus;

    /** 审核人 */
    @Excel(name = "审核人")
    @TableField("username")
    private String username;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    @TableField("apply_time")
    private Date applyTime;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    @TableField("audit_time")
    private Date auditTime;

}
