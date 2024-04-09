package com.ruoyi.charity.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RankUserVo {
    /** 用户ID */
    @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /** 用户昵称 */
    @Excel(name = "用户名称")
    private String nickName;

    /** 用户性别 */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 用户积分 */
    @Excel(name = "用户积分")
    private Integer credit;

    /** 参与投票的次数 */
    @Excel(name = "参与投票的次数")
    private Integer voteCount;

    /** 提现的次数 */
    @Excel(name = "提现的次数")
    private Integer withdrawCount;

    /** 用户的地址 */
    @Excel(name = "用户的地址")
    private String userAddress;

}
