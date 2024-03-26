import request from '@/utils/request'

export function getRankUserList() {
    return request({
        url: '/rank/list',
        method: 'get',
        })
}