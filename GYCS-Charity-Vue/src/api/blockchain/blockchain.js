import request from '@/utils/request'

// 查询公益活动溯源记录列表
export function getBlockInfo() {
    return request({
        url: '/block_chain/blockNumberAndTransactionsInfo',
        method: 'get'
    })
}

export function getNodeInfo() {
        return request({
            url: '/block_chain/getNodeInfo',
            method: 'get'
        })
}

export function getTransactionInfo(query) {
    return request({
        url: '/block_chain/get_transaction_info',
        method: 'get',
        params: query
    })
}