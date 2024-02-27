package com.ruoyi.system.service;

import com.ruoyi.charity.domain.vo.RegisterVo;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * @author zyh
 * @date 2024/2/26 9:05
 * @desc IntelliJ IDEA
 */
public interface ICharityRegister {
    AjaxResult register(RegisterVo registerVo);
}
