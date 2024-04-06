package com.ruoyi.charity.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.util.Date;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("charity_order")
public class CharityOrder {

    @TableId(type = IdType.AUTO)
    private int id;

    @TableField(value = "order_uuid")
    private String orderUuid;

    @TableField(value = "delivery_message")
    private String deliveryMessage;

    @TableField(value = "sign_message")
    private String signMessage;

    @TableField(value = "end_message")
    private String endMessage;

    @TableField(value = "logistic_address")
    private String logisticAddress;

    @TableField(value = "source_address")
    private String sourceAddress;

    @TableField(value = "dest_address")
    private String destAddress;

    @TableField(value = "amount")
    private BigInteger amount;

    @TableField(value = "is_sign")
    private Boolean isSign;

    @TableField(value = "activity_id")
    private BigInteger activityId;

    @TableField(value = "trace_id")
    private BigInteger traceId;

    @TableField(value = "create_time")
    private Date createTime;


    @TableField(value = "item_name")
    private String itemName;
}
