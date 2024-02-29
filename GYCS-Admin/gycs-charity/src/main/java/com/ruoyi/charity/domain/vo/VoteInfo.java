package com.ruoyi.charity.domain.vo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class VoteInfo implements Serializable {

    public BigInteger isYes;

    public BigInteger isNo;

    public Boolean isTrue;

}