import request from '@/utils/request'

// 查询支付分类的雷达图数据
export function selectTransactionTypeTotalData() {
    return request({
        url: '/data/trans_type',
        method: 'get',
    })
}


// 查询每周的交易金额和交易数量
export function selectTransactionByWeek() {
    return request({
        url: '/data/trans_week',
        method: 'get',
    })
}


export function selectBankTransferByWeek(query) {
    return request({
        url: '/data/bank_transfer_week',
        method: 'get',
        params: query
    })
}


export function selectUserOrderStatusByMonth() {
    return request({
        url: '/data/order_status',
        method: 'get',
    })
}