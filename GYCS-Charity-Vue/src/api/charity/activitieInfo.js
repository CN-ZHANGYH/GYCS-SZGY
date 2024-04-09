import request from '@/utils/request'

// 查询公益灾区捐赠活动列表
export function listActivitieInfo(query) {
  return request({
    url: '/charity/activitieInfo/list',
    method: 'get',
    params: query
  })
}

// 查询公益灾区捐赠活动详细
export function getActivitieInfo(id) {
  return request({
    url: '/charity/activitieInfo/' + id,
    method: 'get'
  })
}

// 新增公益灾区捐赠活动
export function addActivitieInfo(data) {
  return request({
    url: '/charity/activitieInfo',
    method: 'post',
    data: data
  })
}

// 修改公益灾区捐赠活动
export function updateActivitieInfo(data) {
  return request({
    url: '/charity/activitieInfo',
    method: 'put',
    data: data
  })
}

// 删除公益灾区捐赠活动
export function delActivitieInfo(id) {
  return request({
    url: '/charity/activitieInfo/' + id,
    method: 'delete'
  })
}
