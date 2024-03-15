package com.ruoyi.charity.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * 物流商用户信息对象 charity_logistic
 *
 * @author zyh
 * @date 2024-02-01
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("charity_logistic")
public class Logistic implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 物流商ID */
    private Long id;

    /** 物流商的名称 */
    @Excel(name = "物流商的名称")
    @TableField(value = "log_name")
    private String logName;

    /** 物流商的地址 */
    @Excel(name = "物流商的地址")
    @TableField(value = "log_address")
    private String logAddress;

    /** 用户的私钥 */
    @Excel(name = "用户的私钥")
    @TableField(value = "private_key")
    private String privateKey;

    /** 用户的公钥 */
    @Excel(name = "用户的公钥")
    @TableField(value = "public_key")
    private String publicKey;


}
