package com.ruoyi.charity.service.impl;

import com.ruoyi.charity.domain.vo.UserKey;
import com.ruoyi.charity.service.UserKeyService;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.springframework.stereotype.Service;

/**
 * @author zyh
 * @date 2024/1/31 22:43
 * @desc 生成用户的区块链账户的业务实现接口
 */
@Service
public class UserKeyServiceImpl implements UserKeyService {

    /**
     * 创建区块链账户接口
     * @return UserKey
     */
    @Override
    public UserKey createBlockChainUser() {
        CryptoSuite cryptoSuite = new CryptoSuite(CryptoType.ECDSA_TYPE);
        CryptoKeyPair keyPair = cryptoSuite.createKeyPair();
        UserKey userKey = new UserKey();
        userKey.setUserAddress(keyPair.getAddress());
        userKey.setPrivateKey(keyPair.getHexPrivateKey());
        userKey.setPublicKey(keyPair.getHexPublicKey());
        return userKey;
    }
}
