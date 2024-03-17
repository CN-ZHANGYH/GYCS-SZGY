import request from '@/utils/request'

// 查询机构用户信息列表
export function listOrg(query) {
  return request({
    url: '/charity/org/list',
    method: 'get',
    params: query
  })
}

// 查询机构用户信息详细
export function getOrg(id) {
  return request({
    url: '/charity/org/' + id,
    method: 'get'
  })
}

// 新增机构用户信息
export function addOrg(data) {
  return request({
    url: '/charity/org',
    method: 'post',
    data: data
  })
}

// 修改机构用户信息
export function updateOrg(data) {
  return request({
    url: '/charity/org',
    method: 'put',
    data: data
  })
}

// 删除机构用户信息
export function delOrg(id) {
  return request({
    url: '/charity/org/' + id,
    method: 'delete'
  })
}


export function getOrgAddress() {
  return request({
    url: '/user/org_address/',
    method: 'post'
  })
}
