package com.ruoyi.charity.domain.dto;

import java.io.Serializable;
import java.math.BigInteger;
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

import javax.validation.constraints.NotNull;

/**
 * 公益灾区捐赠活动对象 charity_activitie_info
 *
 * @author zyh
 * @date 2024-02-01
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("charity_activitie_info")
public class CharityActivitieInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 活动ID */
    private Long id;

    /** 活动名称 */
    @Excel(name = "活动名称")
    @TableField(value = "title")
    @NotNull(message = "活动名称不能为空")
    private String title;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    @TableField(value = "create_time")
    private String createTime;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    @TableField(value = "start_time")
    @NotNull(message = "开始时间不能为空")
    private String startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    @TableField(value = "end_time")
    @NotNull(message = "结束时间不能为空")
    private String endTime;

    /** 物流方式 */
    @Excel(name = "物流方式")
    @TableField(value = "logistic_type")
    @NotNull(message = "物流方式不能为空")
    private String logisticType;

    /** 物流商地址 */
    @Excel(name = "物流商地址")
    @TableField(value = "logistic_address")
    @NotNull(message = "物流商地址不能为空")
    private String logisticAddress;

    /** 代理人地址 */
    @Excel(name = "代理人地址")
    @TableField(value = "lncome_address")
    @NotNull(message = "代理人地址不能为空")
    private String lncomeAddress;

    /** 状态 */
    @Excel(name = "状态")
    @TableField(value = "status")
    private BigInteger status;

    /** 总参与人数 */
    @Excel(name = "总参与人数")
    @TableField(value = "total_people")
    private Long totalPeople;

    /** 总物资数量 */
    @Excel(name = "总物资数量")
    @TableField(value = "total_materias")
    private Long totalMaterias;

}
