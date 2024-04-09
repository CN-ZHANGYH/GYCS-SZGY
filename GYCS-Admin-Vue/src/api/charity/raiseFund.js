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




export function initRaiseFund(data) {
    return request({
        url: '/raise_fund/initiate',
        method: 'post',
        data: data
    })
}


export function uploadCertificate(data) {
    return request({
        url: '/raise_fund/uploadCertificate',
        method: 'post',
        data: data
    })
}

