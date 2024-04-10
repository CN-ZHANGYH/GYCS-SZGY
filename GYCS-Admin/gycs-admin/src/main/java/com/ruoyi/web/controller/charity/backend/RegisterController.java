package com.ruoyi.web.controller.charity.backend;

import com.ruoyi.charity.domain.vo.RegisterVo;
import com.ruoyi.system.service.ICharityRegister;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zyh
 * @date 2024/2/26 8:57
 * @desc 普通用户注册功能
 */


@RestController
@RequestMapping("/register")
public class RegisterController extends BaseController {

    @Autowired
    public ICharityRegister charityRegister;


    @PostMapping
    public AjaxResult register(@Valid @RequestBody RegisterVo registerVo) {
        return charityRegister.register(registerVo);
    }
}
