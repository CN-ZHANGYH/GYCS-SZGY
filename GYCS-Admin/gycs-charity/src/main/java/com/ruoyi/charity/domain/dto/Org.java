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

/**
 * 机构用户信息对象 charity_org
 *
 * @author zyh
 * @date 2024-01-31
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("charity_org")
public class Org
{
    private static final long serialVersionUID = 1L;

    /** 机构ID */
    private Long id;

    /** 机构的名称 */
    @Excel(name = "机构的名称")
    @TableField(value = "org_name")
    private String orgName;

    /** 机构的账户余额 */
    @Excel(name = "机构的账户余额")
    @TableField(value = "amount")
    private Long amount;

    /** 机构的地址 */
    @Excel(name = "机构的地址")
    @TableField(value = "org_address")
    private String orgAddress;

    /** 用户的私钥 */
    @Excel(name = "用户的私钥")
    @TableField(value = "private_key")
    private String privateKey;

    /** 用户的公钥 */
    @Excel(name = "用户的公钥")
    @TableField(value = "public_key")
    private String publicKey;

}
