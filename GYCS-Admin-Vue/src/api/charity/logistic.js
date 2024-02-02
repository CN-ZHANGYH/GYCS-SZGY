import request from '@/utils/request'

// 查询物流商用户信息列表
export function listLogistic(query) {
  return request({
    url: '/charity/logistic/list',
    method: 'get',
    params: query
  })
}

// 查询物流商用户信息详细
export function getLogistic(id) {
  return request({
    url: '/charity/logistic/' + id,
    method: 'get'
  })
}

// 新增物流商用户信息
export function addLogistic(data) {
  return request({
    url: '/charity/logistic',
    method: 'post',
    data: data
  })
}

// 修改物流商用户信息
export function updateLogistic(data) {
  return request({
    url: '/charity/logistic',
    method: 'put',
    data: data
  })
}

// 删除物流商用户信息
export function delLogistic(id) {
  return request({
    url: '/charity/logistic/' + id,
    method: 'delete'
  })
}
