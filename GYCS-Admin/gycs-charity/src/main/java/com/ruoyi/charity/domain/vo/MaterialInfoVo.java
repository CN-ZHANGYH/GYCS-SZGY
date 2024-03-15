package com.ruoyi.charity.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zyh
 * @date 2024/3/15 9:55
 * @desc IntelliJ IDEA
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MaterialInfoVo implements Serializable {


    @NotNull(message = "用户地址不能为空")
    private String _userAddress;

    @NotNull(message = "物资类型不能为空")
    private Integer _materialType;

    @NotNull(message = "物资名称不能为空")
    private String _materialName;

    @NotNull(message = "物资数量不能为空")
    private Integer _materialCount;

    @NotNull(message = "物流商地址不能为空")
    private String _logisticAddress;

    @NotNull(message = "代理机构地址不能为空")
    private String  _destAddress;

    @NotNull(message = "公益活动ID不能为空")
    private Integer _activiteId;


}
