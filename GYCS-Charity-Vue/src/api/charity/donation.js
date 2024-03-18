import request from '@/utils/request'

// 查询捐款信息记录列表
export function listDonation(query) {
  return request({
    url: '/charity/donation/list',
    method: 'get',
    params: query
  })
}

// 查询捐款信息记录详细
export function getDonation(donationId) {
  return request({
    url: '/charity/donation/' + donationId,
    method: 'get'
  })
}

// 新增捐款信息记录
export function addDonation(data) {
  return request({
    url: '/charity/donation',
    method: 'post',
    data: data
  })
}

// 修改捐款信息记录
export function updateDonation(data) {
  return request({
    url: '/charity/donation',
    method: 'put',
    data: data
  })
}

// 删除捐款信息记录
export function delDonation(donationId) {
  return request({
    url: '/charity/donation/' + donationId,
    method: 'delete'
  })
}
