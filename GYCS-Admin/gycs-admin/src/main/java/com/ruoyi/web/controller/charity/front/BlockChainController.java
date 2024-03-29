package com.ruoyi.web.controller.charity.front;

import com.ruoyi.charity.service.BlockChainService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyh
 * @date 2024/3/5 8:35
 * @desc IntelliJ IDEA
 */

@RestController
@RequestMapping("/block_chain")
public class BlockChainController {


    @Autowired
    private BlockChainService blockChainService;

    @GetMapping("/blockNumberAndTransactionsInfo")
    public AjaxResult getBlockInfo(){
        return blockChainService.getBlockInfo();
    }

    @GetMapping("/getNodeInfo")
    public AjaxResult getNodeInfo(){
        return blockChainService.getNodeInfo();
    }
}
