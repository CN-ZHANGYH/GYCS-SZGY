import request from '@/utils/request'

// 删除机构用户信息
export function uploadImage(data) {
    return request({
        url: '/image/upload',
        method: 'post',
        data: data,
        headers: {
            'Content-Type': 'multipart/form-data'
        }})
}
