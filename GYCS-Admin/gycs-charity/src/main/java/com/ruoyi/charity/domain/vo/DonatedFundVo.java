package com.ruoyi.charity.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

/**
 * @author zyh
 * @date 2024/3/4 8:42
 * @desc IntelliJ IDEA
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DonatedFundVo {

    @NotNull(message = "捐款人不能为空")
    private String _donorAddress;

    @NotNull(message = "收款人不能为空")
    private String _destAddress;

    @NotNull(message = "金额不能为空")
    private BigInteger _amount;

    @NotNull(message = "交易类型不能为空")
    private String _transType;


    @NotNull(message = "来源不能为空")
    private String _source;

    @NotNull(message = "描述不能为空")
    private String _desc;

    @NotNull(message = "公益活动不能为空")
    private BigInteger _raiseId;
}
