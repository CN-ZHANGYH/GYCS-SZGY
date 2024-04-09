package com.ruoyi.charity.domain.dto;

import java.math.BigInteger;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
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

@Data
@ToString
@TableName("charity_donation")
public class DonationTrace
{
    private static final long serialVersionUID = 1L;

    /** 公益溯源ID */
    private Long donationId;

    /** 捐款人地址 */
    @Excel(name = "捐款人地址")
    @TableField(value = "donor_address")
    private String donorAddress;

    /** 捐款金额 */
    @Excel(name = "捐款金额")
    @TableField(value = "amount")
    private BigInteger amount;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "trans_time")
    private String transTime;

    /** 交易类型 */
    @Excel(name = "交易类型")
    @TableField(value = "trans_type")
    private String transType;

    /** 交易状态 */
    @Excel(name = "交易状态")
    @TableField("is_donation")
    private Boolean isDonation;

    /** 捐款来源 */
    @Excel(name = "捐款来源")
    @TableField(value = "source")
    private String source;

    /** 捐款描述 */
    @Excel(name = "捐款描述")
    @TableField(value = "description")
    private String description;

    /** 捐款接收方机构 */
    @Excel(name = "捐款接收方机构")
    @TableField(value = "dest_address")
    private String destAddress;

    /** 集资ID */
    @Excel(name = "集资ID")
    @TableField(value = "raise_id")
    private BigInteger raiseId;

}
