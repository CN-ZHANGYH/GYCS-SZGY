import request from '@/utils/request'

// 查询捐款信息记录列表
export function donationMaterial(data) {
    return request({
        url: '/material/donation',
        method: 'post',
        data: data
    })
}



