package com.ruoyi.charity.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusVo {

    private Integer totalOrders;

    private Integer signedOrders;

    private Integer unsignedOrders;

    private Double signedPercentage;

    private Double unsignedPercentage;
}
