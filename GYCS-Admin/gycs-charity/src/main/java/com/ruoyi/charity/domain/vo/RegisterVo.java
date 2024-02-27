package com.ruoyi.charity.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author zyh
 * @date 2024/2/26 9:02
 * @desc 注册实体类
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVo {

    /**
     * 唯一标识
     */
    private Long uuid;

    @NotNull(message = "用户名不能为空")
    public String username;

    @NotNull(message = "密码不能为空")
    public String password;

    @NotNull(message = "验证码不能为空")
    public String code;
}
