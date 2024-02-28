package com.ruoyi.charity.domain.vo;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CertificateInfoVo {


    @NotNull(message = "公益募资活动编号不能为空")
    private Long raiseId;

    @NotNull(message = "身份证不能为空")
    private String cardId;

    @NotNull(message = "姓名不能为空")
    private String name;

    @NotNull(message = "地址不能为空")
    private String address;

    @NotNull(message = "手机号码不能为空")
    private String phone;

    @NotNull(message = "募资证明不能为空")
    private String raiseImage;

    @NotNull(message = "第三方机构证明不能为空")
    private String orgImage;
}
