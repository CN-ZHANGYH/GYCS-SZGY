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


// 查询所有的公益募资活动
export function getRaiseFundList(query) {
    return request({
        url: '/charity/fund/list_by_user',
        method: 'get',
        params: query
    })
}

export function getRaiseFundDataTotal(query) {
    return request({
        url: '/data/raise_fund/total',
        method: 'get',
        params: query
    })
}




export function bankToBankTransfer(data) {
    return request({
        url: '/raise_fund/donation_bank_transfer',
        method: 'post',
        data: data
    })
}


export function getRaiseFundTransactionTrace(query){
    return request({
        url: '/raise_fund/trace',
        method: 'get',
        params: query
    })
}


export function donationRaiseFund(data){
    return request({
        url: '/raise_fund/donation',
        method: 'post',
        data: data
    })
}


export function getRaiseFundWaitVotesList() {
    return request({
        url: '/raise_fund/vote_list',
        method: 'get'
    })
}


export function getRaiseFundInfo(query){
    return request({
        url: '/raise_fund/info',
        method: 'get',
        params: query
    })
}


export function voteOfRaiseFund(query){
    return request({
        url: '/raise_fund/vote',
        method: 'post',
        params: query
    })
}


export function selectBankTransferInfo(query){
    return request({
        url: '/raise_fund/donation_bank_transfer_info',
        method: 'post',
        params: query
    })
}