package com.ruoyi.charity.domain.dto;


import lombok.Data;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


@Data
public class EmailMessageDto {

    // 发送的邮箱账户
    private String account;

    // 发送的邮件标题
    private String title;

    private String userAddress;

    private Integer status;



}
