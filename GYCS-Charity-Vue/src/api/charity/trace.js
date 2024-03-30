import request from "@/utils/request.js";


export function selectTraceByCardId(query) {
    return request({
        url: '/trace/raise_fund_material',
        method: 'post',
        params: query
    })
}

export function selectMaterialDetailByActivityId(query) {
    return request({
        url: '/trace/material_detail',
        method: 'post',
        params: query
    })
}

export function selectRaiseFundDetailByRaiseId(query) {
    return request({
        url: '/trace/raise_fund_detail',
        method: 'post',
        params: query
    })
}
