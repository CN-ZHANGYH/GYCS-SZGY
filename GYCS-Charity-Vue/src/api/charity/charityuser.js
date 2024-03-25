import request from '@/utils/request'

// 查询用户列表
export function listCharityuser(query) {
  return request({
    url: '/charity/charityuser/list',
    method: 'get',
    params: query
  })
}

// 查询用户详细
export function getCharityuser(id) {
  return request({
    url: '/charity/charityuser/' + id,
    method: 'get'
  })
}

// 新增用户
export function addCharityuser(data) {
  return request({
    url: '/charity/charityuser',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateCharityuser(data) {
  return request({
    url: '/charity/charityuser',
    method: 'put',
    data: data
  })
}

// 删除用户
export function delCharityuser(id) {
  return request({
    url: '/charity/charityuser/' + id,
    method: 'delete'
  })
}

// 用户绑定银行卡信息
export function bindBankCard(data) {
  return request({
    url: '/user/bind_bank',
    method: 'post',
    data: data
  })
}


export function getUserBindBankInfo() {
  return request({
    url: '/user/get_bind_bank_info',
    method: 'post'
  })
}


// 查看用户的详细信息
export function getUserProfileInfo() {
  return request({
    url: '/user/profile',
    method: 'get'
  })
}
