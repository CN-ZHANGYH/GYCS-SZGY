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

/**
 * @author zyh
 * @date 2024/3/13 23:27
 * @desc IntelliJ IDEA
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("charity_donation_transaction")
public class DonationTransaction {


    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "transaction_hash")
    private String transactionHash;

    @TableField(value = "block_number")
    private BigInteger blockNumber;

    @TableField(value = "status")
    private Boolean status;

    @TableField(value = "raise_id")
    private BigInteger raiseId;


}
