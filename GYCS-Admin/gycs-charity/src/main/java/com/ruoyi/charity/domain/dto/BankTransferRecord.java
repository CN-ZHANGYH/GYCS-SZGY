package com.ruoyi.charity.domain.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

import java.math.BigInteger;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("charity_bank_transfer_record")
public class BankTransferRecord {

    private Integer id;

    @TableField("raise_id")
    private BigInteger raiseId;

    @TableField("amount")
    private BigInteger amount;

    @TableField("trans_time")
    private Date transTime;
}
