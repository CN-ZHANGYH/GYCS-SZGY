package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.charity.domain.vo.CertificateInfoVo;
import com.ruoyi.charity.service.RaiseFundService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/raise_fund")
public class RaiseFundController {


    @Autowired
    private RaiseFundService fundService;


    /**
     * 发起公益募资接口
     * @param charityRaiseFund
     * @return AjaxResult
     */
    @PostMapping("/initiate")
    public AjaxResult initiateRaiseFund(@Valid @RequestBody CharityRaiseFund charityRaiseFund) {
        return fundService.initiateRaiseFund(charityRaiseFund);
    }


    @PostMapping("/uploadCertificate")
    public AjaxResult uploadCertificate(@Valid @RequestBody CertificateInfoVo certificateInfoVo) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return fundService.uploadCertificate(certificateInfoVo,loginUser.getUsername());
    }
}
