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

export function getUserAddress() {
  return request({
    url: '/user/user_address',
    method: 'post'
  })
}


