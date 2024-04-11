import request from '@/utils/request'

// 绑定账号
export function authBinding() {
    return request({
        url: '/system/auth/binding/',
        method: 'get'
    })
}

// 解绑账号
export function authUnlock(authId) {
    return request({
        url: '/system/auth/unlock/' + authId,
        method: 'delete'
    })
}

// 第三方平台登录
export function socialLogin(code, state) {
    const data = {
        code,
        state
    }
    return request({
        url: '/system/auth/social-login/',
        method: 'get',
        params: data
    })
}
