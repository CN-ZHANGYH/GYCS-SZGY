package com.ruoyi.charity.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDataVo {

    private String countDay;

    private Integer countDelivery;

    private Integer countSign;

    private Integer countRate;
}
