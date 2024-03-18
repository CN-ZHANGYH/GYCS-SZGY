import request from '@/utils/request'



export function getCertificateInfo(query) {
    return request({
        url: '/raise_fund/getCertificateInfo',
        method: 'get',
        params: query
    })
}




export function getRaiseFundDetail(query) {
    return request({
        url: '/raise_fund/detail',
        method: 'get',
        params: query
    })
}
