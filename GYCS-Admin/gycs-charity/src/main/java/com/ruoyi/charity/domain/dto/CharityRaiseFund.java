package com.ruoyi.charity.domain.dto;

import java.util.Date;
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
 * 公益募资活动对象 charity_raise_fund
 *
 * @author zyh
 * @date 2024-02-01
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CharityRaiseFund extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公益慈善ID */
    private Long id;

    /** 活动名称 */
    @Excel(name = "活动名称")
    @NotNull(message = "活动名称不能为空")
    private String title;

   /** 活动描述 */
    @Excel(name = "活动描述")
    @NotNull(message = "活动描述不能为空")
    private String desc;

    /** 活动组织者 */
    @Excel(name = "活动组织者")
    @NotNull(message = "活动组织者不能为空")
    private String promoterAddress;

    /** 活动状态 */
    @Excel(name = "活动状态")
    private Long status;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "开始时间不能为空")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "结束时间不能为空")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String endTime;

    /** 总参与人数 */
    @Excel(name = "总参与人数")
    private Long totalPeople;

    /** 总需金额 */
    @Excel(name = "总需金额")
    @NotNull(message = "总需金额不能为空")
    private Long totalAmount;

    /** 已完成金额 */
    @Excel(name = "已完成金额")
    private Long overAmount;

    /** 已取出金额 */
    @Excel(name = "已取出金额")
    private Long withdrawAmount;
}
