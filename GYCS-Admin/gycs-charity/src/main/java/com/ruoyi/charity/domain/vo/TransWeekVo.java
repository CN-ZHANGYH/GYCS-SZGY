package com.ruoyi.charity.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zyh
 * @date 2024/3/27 17:54
 * @desc IntelliJ IDEA
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransWeekVo {

    public String week;

    public Integer transAmount;

    public Integer transTotal;
}
