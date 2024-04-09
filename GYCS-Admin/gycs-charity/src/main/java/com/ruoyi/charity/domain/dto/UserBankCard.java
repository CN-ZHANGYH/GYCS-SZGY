package com.ruoyi.charity.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author zyh
 * @date 2024/3/25 10:56
 * @desc IntelliJ IDEA
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("charity_user_bank")
public class UserBankCard {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "bank_account")
    @NotNull(message = "开户账户为空")
    private String bankAccount;

    @TableField(value = "bank_name")
    @NotNull(message = "开户银行为空")
    private String bankName;

    @TableField(value = "username")
    @NotNull(message = "开户姓名为空")
    private String username;

    @TableField("card_id")
    @NotNull(message = "身份证号为空")
    private String cardId;

    @TableField(value = "address")
    @NotNull(message = "工作地址为空")
    private String address;

    @TableField("user_id")
    private Long userId;

}
