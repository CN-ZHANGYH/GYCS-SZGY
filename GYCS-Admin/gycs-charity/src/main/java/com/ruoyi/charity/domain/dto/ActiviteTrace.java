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
 * 公益活动溯源记录对象 charity_activite
 *
 * @author zyh
 * @date 2024-02-01
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("charity_activite")
public class ActiviteTrace
{
    private static final long serialVersionUID = 1L;

    /** 公益活动溯源ID */
    private Long charityId;

    /** 种类 */
    @Excel(name = "种类")
    @TableField(value = "material_type")
    private Long materialType;

    /** 物资的名称 */
    @Excel(name = "物资的名称")
    @TableField(value = "material_name")
    private String materialName;

    /** 物资的数量 */
    @Excel(name = "物资的数量")
    @TableField(value = "material_count")
    private Long materialCount;

    /** 源地址 */
    @Excel(name = "源地址")
    @TableField(value = "source_address")
    private String sourceAddress;

    /** 物流商地址 */
    @Excel(name = "物流商地址")
    @TableField(value = "logistic_address")
    private String logisticAddress;

    /** 目标地址 */
    @Excel(name = "目标地址")
    @TableField(value = "dest_address")
    private String destAddress;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    @TableField(value = "trans_time")
    private String transTime;

    /** 是否签收 */
    @Excel(name = "是否签收")
    @TableField(value = "is_sign")
    private Boolean isSign;

    /** 公益活动ID */
    @Excel(name = "公益活动ID")
    @TableField(value = "activit_id")
    private Long activitId;

    /** 溯源状态 */
    @Excel(name = "溯源状态")
    @TableField(value = "status")
    private Integer status;

    /** 溯源的地址 */
    @Excel(name = "溯源的地址")
    @TableField(value = "trace_address")
    private String traceAddress;

    /** 溯源的时间 */
    @Excel(name = "溯源的时间")
    @TableField(value = "trace_time")
    private String traceTime;

}
