package com.ruoyi.charity.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author zyh
 * @date 2024/3/6 11:25
 * @desc IntelliJ IDEA
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BankTransferRecordVo implements Serializable {

    private BigInteger raiseId;

    /**
     *  捐款人姓名
     */
    @NotNull(message = "捐款人姓名不能为空")
    private String donorName;

    /**
     *  捐款人身份证ID
     */
    @NotNull(message = "捐款人身份证ID不能为空")
    private String donorCardId;

    /**
     *  捐款人工作单位
     */
    @NotNull(message = "捐款人工作单位不能为空")
    private String donorWorkUnit;

    /**
     *  捐款人开户银行
     */
    @NotNull(message = "捐款人开户银行不能为空")
    private String donorBank;

    /**
     *  捐款人开户账户
     */
    @NotNull(message = "捐款人开户账户不能为空")
    private String donorAccount;

    /**
     *  捐款人捐款金额
     */
    @NotNull(message = "捐款人捐款金额不能为空")
    private BigInteger donorAmount;

    /**
     *  捐款人捐款金额中文
     */
    @NotNull(message = "捐款人捐款金额中文不能为空")
    private String donorAmountCN;

    /**
     * 捐款人描述信息
     */
    @NotNull(message = "捐款人描述信息不能为空")
    private String donorDescription;

    /**
     *  捐款人备注信息
     */
    @NotNull(message = "捐款人备注信息不能为空")
    private String donorRemarks;

    /**
     *  交易时间
     */
    private String transTime;

    /**
     *  受助人姓名
     */
    @NotNull(message = "受助人姓名不能为空")
    private String aidedName;

    /**
     *  捐款人身份证ID
     */
    @NotNull(message = "捐款人身份证ID不能为空")
    private String aideCardId;

    /**
     *  受助人工作单位
     */
    @NotNull(message = "受助人工作单位不能为空")
    private String aideWorkUnit;

    /**
     *  受助人开户银行
     */
    @NotNull(message = "受助人开户银行不能为空")
    private String aideBank;

    /**
     *  捐款人开户账户
     */
    @NotNull(message = "捐款人开户账户不能为空")
    private String aideAccount;



}
