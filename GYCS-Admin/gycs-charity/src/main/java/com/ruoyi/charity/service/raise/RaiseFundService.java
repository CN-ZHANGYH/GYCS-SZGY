package com.ruoyi.charity.service.raise;

import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.charity.domain.vo.BankTransferRecordVo;
import com.ruoyi.charity.domain.vo.CertificateInfoVo;
import com.ruoyi.charity.domain.vo.DonatedFundVo;
import com.ruoyi.charity.domain.vo.DonationTraceVo;
import com.ruoyi.common.core.domain.AjaxResult;

import java.math.BigInteger;
import java.util.List;

public interface RaiseFundService {
    AjaxResult initiateRaiseFund(CharityRaiseFund charityRaiseFund);

    AjaxResult uploadCertificate(CertificateInfoVo certificateInfoVo, String username);

    AjaxResult getRaiseFundInfo(Long raiseId,String username);

    AjaxResult getCertificateInfo(BigInteger raiseId,String username);

    AjaxResult getRaiseFundVoteStatus(Long raiseId, String username);

    AjaxResult voteOfRaiseFund(Long raiseId, Boolean status, String username);

    AjaxResult getRaiseFundDetail(Long raiseId);


    AjaxResult donation(DonatedFundVo donatedFundVo,String username);

    List<DonationTraceVo> getRaiseFundTrace(BigInteger raiseId);

    AjaxResult donationBankTransfer(BankTransferRecordVo bankTransferRecordVo);

    AjaxResult getDonationBankTransferInfo(String  cardId);

    AjaxResult getDonationBankTransferRecordList(Integer pageNum, Integer pageSize,BigInteger raiseId);

    AjaxResult selectRaiseFundTotalData(BigInteger raiseId);

    AjaxResult selectRaiseFundVotesList();

}
