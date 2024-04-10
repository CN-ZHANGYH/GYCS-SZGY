import request from '@/utils/request'

// 查询公益活动溯源记录列表
export function listActivite(query) {
  return request({
    url: '/charity/activite/list',
    method: 'get',
    params: query
  })
}

// 查询公益活动溯源记录详细
export function getActivite(charityId) {
  return request({
    url: '/charity/activite/' + charityId,
    method: 'get'
  })
}

// 新增公益活动溯源记录
export function addActivite(data) {
  return request({
    url: '/charity/activite',
    method: 'post',
    data: data
  })
}

// 修改公益活动溯源记录
export function updateActivite(data) {
  return request({
    url: '/charity/activite',
    method: 'put',
    data: data
  })
}

// 删除公益活动溯源记录
export function delActivite(charityId) {
  return request({
    url: '/charity/activite/' + charityId,
    method: 'delete'
  })
}


// 发起公益活动接口
export function initActivite(data) {
  return request({
    url: '/activity/initiate/',
    method: 'post',
    data: data
  })
}


// 查看所有公益活动接口
export function getActivityList(query) {
  return request({
    url: '/activity/list_by_user',
    method: 'get',
    params: query
  })
}
