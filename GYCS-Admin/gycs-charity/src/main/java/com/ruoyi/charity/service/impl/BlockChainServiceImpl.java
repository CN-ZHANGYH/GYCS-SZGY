package com.ruoyi.charity.service.impl;

import com.ruoyi.charity.service.BlockChainService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.aspectj.weaver.ast.Var;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.model.JsonTransactionResponse;
import org.fisco.bcos.sdk.client.protocol.response.ConsensusStatus;
import org.fisco.bcos.sdk.client.protocol.response.TotalTransactionCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
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
    public AjaxResult getBlockInfo() {
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

    @Override
    public AjaxResult getNodeInfo() {
        ConsensusStatus.ConsensusInfo consensusStatus = client.getConsensusStatus().getConsensusStatus();
        AjaxResult success = AjaxResult.success();
        ArrayList<HashMap<String, Object>> result = new ArrayList<>();
        String blockNumber = consensusStatus.getBaseConsensusInfo().getHighestblockNumber();
        for (int i = 0; i < consensusStatus.getViewInfos().size(); i++) {
            String nodeId = consensusStatus.getViewInfos().get(i).getNodeId();
            String pbftView = consensusStatus.getViewInfos().get(i).getView();

            HashMap<String, Object> data = new HashMap<>();
            data.put("id",nodeId);
            data.put("blockNumber",blockNumber);
            data.put("pbftView",pbftView);
            data.put("status",true);

            result.add(data);
        }
        return success.put("data",result);
    }
}
