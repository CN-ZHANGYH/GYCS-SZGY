package com.ruoyi.charity.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage {
    private Integer orderId;
    private String  deliveryMessage;
    private String  signMessage;
    private String  endMessage;
}
