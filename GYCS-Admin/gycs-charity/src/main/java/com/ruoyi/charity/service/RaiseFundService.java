package com.ruoyi.charity.service;

import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.common.core.domain.AjaxResult;

public interface RaiseFundService {
    AjaxResult initiateRaiseFund(CharityRaiseFund charityRaiseFund);
}
