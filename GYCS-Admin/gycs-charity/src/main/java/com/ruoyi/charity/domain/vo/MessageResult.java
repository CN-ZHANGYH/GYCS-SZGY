package com.ruoyi.charity.domain.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author zyh
 * @date 2024/2/26 17:08
 * @desc IntelliJ IDEA
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResult {

    public String message;

    public String data;

    public Integer code;

}
