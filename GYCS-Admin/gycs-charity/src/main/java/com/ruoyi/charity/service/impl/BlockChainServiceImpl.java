package com.ruoyi.charity.service.impl;

import com.ruoyi.charity.service.BlockChainService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.aspectj.weaver.ast.Var;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.model.JsonTransactionResponse;
import org.fisco.bcos.sdk.client.protocol.response.TotalTransactionCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author zyh
 * @date 2024/3/5 8:39
 * @desc IntelliJ IDEA
 */

@Service
public class BlockChainServiceImpl implements BlockChainService {



    @Autowired
    private Client client;


    /**
     *  get new blockNumber by blockchain
     */
    @Override
    public AjaxResult getBlockNumber() {
        BigInteger blockNumber = client.getBlockNumber().getBlockNumber();
        List<String> nodeIDList = client.getNodeIDList().getNodeIDList();
        TotalTransactionCount.TransactionCountInfo result = client.getTotalTransactionCount().getResult();
        String hexString = result.getTxSum();
        if (hexString.startsWith("0x")) {
            hexString = hexString.substring(2);
        }
        int totalTransaction = Integer.parseInt(hexString, 16);
        List<JsonTransactionResponse> pendingTransactions = client.getPendingTransaction().getPendingTransactions();

        HashMap<String, Object> data = new HashMap<>();
        data.put("blockNumber",blockNumber);
        data.put("nodeList",nodeIDList.size());
        data.put("totalTransactions",totalTransaction);
        data.put("pendingTransactions",pendingTransactions.size());

        return AjaxResult.success().put("data",data);
    }
}
