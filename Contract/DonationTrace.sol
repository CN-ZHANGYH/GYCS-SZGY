// SPDX-License-Identifier: GPL-3.0

pragma solidity ^0.6.10;

// 整个详细的溯源信息包括: 
//  1.用户捐款的金额和溯源信息
//  2.代理机构作为金额账户管理
//  3.提现的用户根据认证信息可以提现
contract DonationTrace {
    
    uint256 donationTraceId;    // 公益慈善溯源ID

    address donorAddress;       // 捐款人地址

    uint256 amount;             // 捐款金额

    uint256 transTime;          // 交易时间

    string  transType;          // 交易类型

    uint256 raiseId;            // 公益集资活动ID

    bool    isDonation;         // 交易状态

    string  source;             // 捐款来源：来源，可以是个人、组织、企业等。

    string  desc;               // 捐款描述：具体用途，例如救灾、扶贫、医疗救助等。

    address destAddress;        // 捐款接收方机构
    



    // 初始化公益慈善捐款的上链信息
    constructor(
        uint256 _donationTraceId,
        address _donorAddress,
        address _destAddress,
        uint256 _amount,
        string memory _transType,
        string memory _source,
        string memory _desc,
        uint256 _raiseId
    ) public {
        donationTraceId = _donationTraceId;
        donorAddress = _donorAddress;
        destAddress = _destAddress;
        amount = _amount;
        transType = _transType;
        source = _source;
        desc = _desc;
        raiseId = _raiseId;
        transTime = now;
        isDonation = true;
    }

    // 查询当前的公益慈善捐款的溯源信息
    function selectTraceInfo() public returns(
        uint256,
        address,
        address,
        uint256,
        uint256,
        string memory,
        string memory,
        string memory,
        bool,
        uint256
    ) {
        return(donationTraceId,donorAddress,destAddress,amount,transTime,transType,source,desc,isDonation,raiseId);
    }
    
}