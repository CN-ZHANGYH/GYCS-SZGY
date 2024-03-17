package com.ruoyi.charity.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author zyh
 * @date 2024/3/17 19:40
 * @desc IntelliJ IDEA
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationTraceVo {

    private Long donationId;

    private String donorAddress;

    private BigInteger amount;

    private Date transTime;

    private String transType;

    private Boolean isDonation;

    private String source;

    private String description;

    private String destAddress;

    private BigInteger raiseId;

    private String transactionHash;

    private BigInteger blockNumber;

    private Boolean status;

}
