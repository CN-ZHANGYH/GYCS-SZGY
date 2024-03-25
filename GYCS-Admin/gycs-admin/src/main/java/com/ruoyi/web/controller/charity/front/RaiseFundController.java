package com.ruoyi.web.controller.charity.front;


import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.charity.domain.vo.BankTransferRecordVo;
import com.ruoyi.charity.domain.vo.CertificateInfoVo;
import com.ruoyi.charity.domain.vo.DonatedFundVo;
import com.ruoyi.charity.domain.vo.DonationTraceVo;
import com.ruoyi.charity.service.RaiseFundService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/raise_fund")
public class RaiseFundController  extends BaseController {


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


    /**
     * 根据ID查询公益募资详情
     * @param raiseId
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult getRaiseFundDetail(@RequestParam("raiseId") Long raiseId) {
        return fundService.getRaiseFundDetail(raiseId);
    }


    /**
     * 根据ID查询上传的证明信息
     * @param raiseId
     * @return AjaxResult
     */
    @GetMapping("/getCertificateInfo")
    public AjaxResult getCertificateInfo(@RequestParam("raiseId") BigInteger raiseId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return fundService.getCertificateInfo(raiseId,loginUser.getUsername());
    }


    /**
     * 根据ID查询投票状态
     * @param raiseId
     * @return AjaxResult
     */
    @GetMapping("/getVoteStatus")
    public AjaxResult getRaiseFundVoteStatus(@RequestParam("raiseId") Long raiseId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return fundService.getRaiseFundVoteStatus(raiseId,loginUser.getUsername());
    }


    /**
     * 公益募资活动投票功能
     * @param raiseId
     * @param status
     * @return AjaxResult
     */
    @PostMapping("/vote")
    public AjaxResult voteOfRaiseFund(@RequestParam("raiseId") Long raiseId,@RequestParam("status") Boolean status) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return fundService.voteOfRaiseFund(raiseId,status,loginUser.getUsername());
    }


    /**
     * 公益活动捐款功能
     * @param donatedFundVo
     * @return AjaxResult
     */
    @PostMapping("/donation")
    public AjaxResult donation(@Valid @RequestBody DonatedFundVo donatedFundVo){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return fundService.donation(donatedFundVo,loginUser.getUsername());
    }


    /**
     * Get the trace information of the raiseId
     * @param raiseId
     * @return AjaxResult
     */
   @GetMapping("/trace")
    public AjaxResult getRaiseFundTrace(@RequestParam("raiseId") BigInteger raiseId) {
        // Get the raiseId from the request parameter
       AjaxResult success = AjaxResult.success();
       List<DonationTraceVo> raiseFundTrace = fundService.getRaiseFundTrace(raiseId);
       // Return the trace information of the raiseId
       success.put("total",raiseFundTrace.size());
       success.put("rows",raiseFundTrace);
       return success;
    }

   @PostMapping("/donation_bank_transfer")
    public AjaxResult donationBankTransfer(@Valid @RequestBody BankTransferRecordVo bankTransferRecordVo){
        //Call the fundService to transfer the money from the bank account to the user's account
        return fundService.donationBankTransfer(bankTransferRecordVo);
    }


   @PostMapping("/donation_bank_transfer_info")
    //This method is used to get the donation bank transfer info
    public AjaxResult getDonationBankTransferInfo(@RequestParam("cardId") String cardId){
        //This line calls the fundService to get the donation bank transfer info
        return fundService.getDonationBankTransferInfo(cardId);
    }

    @GetMapping("/bank_transfer_record_list")
    //This method is used to get the bank transfer record list
    public AjaxResult getDonationBankTransferRecordList(
            //This line gets the page number from the request parameter
            @RequestParam("pageNum") Integer pageNum,
            //This line gets the page size from the request parameter
            @RequestParam("pageSize") Integer pageSize,
            //This line gets the raiseId from the request parameter
            @RequestParam("raiseId") BigInteger raiseId) {
        //This line calls the fundService to get the bank transfer record list
        return fundService.getDonationBankTransferRecordList(pageNum, pageSize,raiseId);
    }

}
