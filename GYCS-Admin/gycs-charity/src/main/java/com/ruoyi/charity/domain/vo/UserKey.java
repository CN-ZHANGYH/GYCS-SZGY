package com.ruoyi.charity.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zyh
 * @date 2024/1/31 22:38
 * @desc 用户的区块链账户信息
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserKey {

    /**
     * 用户地址
     */
    private String userAddress;


    /**
     * 用户私钥
     */
    private String privateKey;


    /**
     * 用户公钥
     */
    private String publicKey;
}
