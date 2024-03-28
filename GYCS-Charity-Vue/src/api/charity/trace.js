import request from "@/utils/request.js";


export function selectTraceByCardId(query) {
    return request({
        url: '/trace/raise_fund_material',
        method: 'post',
        params: query
    })
}


