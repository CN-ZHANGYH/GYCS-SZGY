package com.ruoyi.charity.service.account;

import com.ruoyi.charity.domain.vo.UserKey;

/**
 * @author zyh
 * @date 2024/1/31 22:43
 * @desc 生成用户的区块链地址接口
 */
public interface UserKeyService {


    /**
     * 创建区块链账户接口
     * @return UserKey
     */
    UserKey createBlockChainUser();
}
