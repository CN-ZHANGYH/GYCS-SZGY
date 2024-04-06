import request from '@/utils/request'

// 查询物流商的订单信息
export function selectOrderIsNotSignList(query) {
    return request({
        url: '/order/list',
        method: 'get',
        params: query
    })
}

export function updateOrderMessage(data) {
    return request({
        url: '/order/update',
        method: 'post',
        data: data
    })
}



export function endOrderById(query) {
    return request({
        url: '/order/end_order_by_id',
        method: 'get',
        params: query
    })
}


export function selectOrderProcessByLogistic() {
    return request({
        url: '/data/order_process',
        method: 'get'
    })
}
