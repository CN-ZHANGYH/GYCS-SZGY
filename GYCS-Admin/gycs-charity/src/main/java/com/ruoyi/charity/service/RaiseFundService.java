package com.ruoyi.charity.service;

import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.charity.domain.vo.CertificateInfoVo;
import com.ruoyi.common.core.domain.AjaxResult;

public interface RaiseFundService {
    AjaxResult initiateRaiseFund(CharityRaiseFund charityRaiseFund);

    AjaxResult uploadCertificate(CertificateInfoVo certificateInfoVo, String username);

    AjaxResult getRaiseFundInfo(Long raiseId,String username);

    AjaxResult getCertificateInfo(Long raiseId,String username);

    AjaxResult getRaiseFundVoteStatus(Long raiseId, String username);

    AjaxResult voteOfRaiseFund(Long raiseId, Boolean status, String username);

    AjaxResult getRaiseFundDetail(Long raiseId);


}
