import request from '@/utils/request'

// 查询审核列表
export function listAudit(query) {
  return request({
    url: '/charity/audit/list',
    method: 'get',
    params: query
  })
}

// 查询审核详细
export function getAudit(id) {
  return request({
    url: '/charity/audit/' + id,
    method: 'get'
  })
}

// 新增审核
export function addAudit(data) {
  return request({
    url: '/charity/audit',
    method: 'post',
    data: data
  })
}

// 修改审核
export function updateAudit(data) {
  return request({
    url: '/charity/audit',
    method: 'put',
    data: data
  })
}

// 删除审核
export function delAudit(id) {
  return request({
    url: '/charity/audit/' + id,
    method: 'delete'
  })
}
