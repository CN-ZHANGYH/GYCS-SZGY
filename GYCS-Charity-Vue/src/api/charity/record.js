import request from "@/utils/request.js";

// 查询用户的所有捐款记录信息
export function selectUserDonationRaiseFundRecord() {
    return request({
        url: '/record/donation/raise_fund/list',
        method: 'get',
    })
}

// 查询用户的所有物资捐赠记录信息
export function selectUserDonationMaterialRecord() {
    return request({
        url: '/record/donation/material/list',
        method: 'get',
    })
}


export function selectTransactionHashAndBlockNumber(query) {
    return request({
        url: '/record/donation/raise_fund/transaction_hash',
        method: 'get',
        params: query
    })
}


export function selectTransactionHashAndBlockNumberByMaterialId(query) {
    return request({
        url: '/record/donation/material/transaction_hash',
        method: 'get',
        params: query
    })
}




export function selectMaterialRelationship(query) {
    return request({
        url: '/record/donation/material/relationship',
        method: 'get',
        params: query
    })
}