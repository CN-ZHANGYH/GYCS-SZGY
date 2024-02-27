package com.ruoyi.charity.domain.vo;


import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserVo {

    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /** 用户账号 */
    @Excel(name = "登录名称")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户名称")
    private String nickName;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 用户性别 */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 用户头像 */
    private String avatar;


    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;


    /** 用户的余额 */
    @Excel(name = "用户的余额")
    private Integer amount;

    /** 用户积分 */
    @Excel(name = "用户积分")
    private Integer credit;

    /** 身份证ID */
    @Excel(name = "身份证ID")
    private String cardId;

    /** 用户称号 */
    @Excel(name = "用户称号")
    private String designation;

    /** 参与投票的次数 */
    @Excel(name = "参与投票的次数")
    private Integer voteCount;

    /** 提现的次数 */
    @Excel(name = "提现的次数")
    private Integer withdrawCount;

    /** 用户的地址 */
    @Excel(name = "用户的地址")
    private String userAddress;

    /** 用户的私钥 */
    @Excel(name = "用户的私钥")
    private String privateKey;

    /** 用户的公钥 */
    @Excel(name = "用户的公钥")
    private String publicKey;
}
