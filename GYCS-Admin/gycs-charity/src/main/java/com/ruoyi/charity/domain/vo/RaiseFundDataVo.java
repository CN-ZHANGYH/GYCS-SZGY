package com.ruoyi.charity.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaiseFundDataVo {

    private Integer id;

    private String name;

    private BigInteger value;
}
