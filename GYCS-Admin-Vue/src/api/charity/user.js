import request from '@/utils/request'

// 查询用户信息表列表
export function listUser(query) {
  return request({
    url: '/charity/user/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息表详细
export function getUser(id) {
  return request({
    url: '/charity/user/' + id,
    method: 'get'
  })
}

// 新增用户信息表
export function addUser(data) {
  return request({
    url: '/charity/user',
    method: 'post',
    data: data
  })
}

// 修改用户信息表
export function updateUser(data) {
  return request({
    url: '/charity/user',
    method: 'put',
    data: data
  })
}

// 删除用户信息表
export function delUser(id) {
  return request({
    url: '/charity/user/' + id,
    method: 'delete'
  })
}
