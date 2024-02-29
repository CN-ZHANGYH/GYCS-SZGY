package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.charity.domain.vo.CertificateInfoVo;
import com.ruoyi.charity.service.RaiseFundService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 上传证明信息
     * @param certificateInfoVo
     * @return AjaxResult
     */
    @PostMapping("/uploadCertificate")
    public AjaxResult uploadCertificate(@Valid @RequestBody CertificateInfoVo certificateInfoVo) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return fundService.uploadCertificate(certificateInfoVo,loginUser.getUsername());
    }


    /**
     * 获取公益募资信息和上传证明信息以及审批的信息
     * @param raiseId
     * @return AjaxResult
     */
    @GetMapping("/info")
    public AjaxResult getRaiseFundInfo(@RequestParam("raiseId") Long raiseId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return fundService.getRaiseFundInfo(raiseId,loginUser.getUsername());
    }


    @GetMapping("/getCertificateInfo")
    public AjaxResult getCertificateInfo(@RequestParam("raiseId") Long raiseId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return fundService.getCertificateInfo(raiseId,loginUser.getUsername());
    }


    @GetMapping("/getVoteStatus")
    public AjaxResult getRaiseFundVoteStatus(@RequestParam("raiseId") Long raiseId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return fundService.getRaiseFundVoteStatus(raiseId,loginUser.getUsername());
    }


    @PostMapping("/vote")
    public AjaxResult voteOfRaiseFund(@RequestParam("raiseId") Long raiseId,@RequestParam("status") Boolean status) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return fundService.voteOfRaiseFund(raiseId,status,loginUser.getUsername());
    }

}
