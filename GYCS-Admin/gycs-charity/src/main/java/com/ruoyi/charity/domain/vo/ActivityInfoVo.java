package com.ruoyi.charity.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityInfoVo
{
    @NotNull(message = "作者不能为空")
    private String author;

    @NotNull(message = "内容不能为空")
    private String content;


    @NotNull(message = "封面不能为空")
    private String  img;

    /** 活动名称 */
    @Excel(name = "活动名称")
    @NotNull(message = "活动名称不能为空")
    private String title;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String createTime;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    @NotNull(message = "开始时间不能为空")
    private String startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    @NotNull(message = "结束时间不能为空")
    private String endTime;

    /** 物流方式 */
    @Excel(name = "物流方式")
    @NotNull(message = "物流方式不能为空")
    private String logisticType;

    /** 物流商地址 */
    @Excel(name = "物流商地址")
    @NotNull(message = "物流商地址不能为空")
    private String logisticAddress;

    /** 代理人地址 */
    @Excel(name = "代理人地址")
    @NotNull(message = "代理人地址不能为空")
    private String lncomeAddress;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 总参与人数 */
    @Excel(name = "总参与人数")
    private Long totalPeople;

    /** 总物资数量 */
    @Excel(name = "总物资数量")
    private Long totalMaterias;
}
