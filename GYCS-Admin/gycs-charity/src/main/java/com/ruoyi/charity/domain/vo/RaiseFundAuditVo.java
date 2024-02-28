package com.ruoyi.charity.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author zyh
 * @date 2024/2/28 19:58
 * @desc IntelliJ IDEA
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaiseFundAuditVo {

    /** 公益慈善ID */
    private Long id;

    /** 活动名称 */
    private String title;

    /** 活动描述 */
    private String description;


    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 活动组织者 */
    private String promoterAddress;

    /** 活动状态 */
    private Long status;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    /** 总参与人数 */
    private BigInteger totalPeople;

    /** 总需金额 */
    private BigInteger totalAmount;

    /** 已完成金额 */
    private BigInteger overAmount;

    /** 已取出金额 */
    private BigInteger withdrawAmount;


    /** 募资活动ID */
    private Long raiseId;

    /** 审核状态 */
    private Integer apply_status;

    /** 审核人 */
    private String username;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;
}
