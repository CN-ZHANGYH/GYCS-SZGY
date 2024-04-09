package com.ruoyi.charity.domain.vo;


import lombok.Data;

import java.math.BigInteger;

@Data
public class BankTransferDataView {

    private Integer dayOfWeek;

    private Integer transactionCount;

    private BigInteger transactionAmount;
}
