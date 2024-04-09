import request from '@/utils/request'

// 查询公益募资活动列表
export function listFund(query) {
  return request({
    url: '/charity/fund/list',
    method: 'get',
    params: query
  })
}

// 查询公益募资活动详细
export function getFund(id) {
  return request({
    url: '/charity/fund/' + id,
    method: 'get'
  })
}

// 新增公益募资活动
export function addFund(data) {
  return request({
    url: '/charity/fund',
    method: 'post',
    data: data
  })
}

// 修改公益募资活动
export function updateFund(data) {
  return request({
    url: '/charity/fund',
    method: 'put',
    data: data
  })
}

// 删除公益募资活动
export function delFund(id) {
  return request({
    url: '/charity/fund/' + id,
    method: 'delete'
  })
}
