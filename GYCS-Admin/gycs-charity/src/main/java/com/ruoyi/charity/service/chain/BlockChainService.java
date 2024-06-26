package com.ruoyi.charity.service.chain;

import com.ruoyi.common.core.domain.AjaxResult;

/**
 * @author zyh
 * @date 2024/3/5 8:39
 * @desc IntelliJ IDEA
 */
public interface BlockChainService {
    AjaxResult getBlockInfo();

    AjaxResult getNodeInfo();

    AjaxResult selectTransactionInfoByHash(String hash);
}
