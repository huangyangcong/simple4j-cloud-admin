import request from '@/utils/request'

export const getPage = (page_no, page_size, params) => {
  return request({
    url: '/flow/actadmdatabasechangelog/page',
    method: 'post',
    data: {
      page_no,
      page_size,
      ...params
    }
  })
}

export const getDetail = (id) => {
  return request({
    url: '/flow/actadmdatabasechangelog/detail',
    method: 'post',
    data: {
      id: id
    }
  })
}

export const remove = (ids) => {
  return request({
    url: '/flow/actadmdatabasechangelog/remove',
    method: 'post',
    data: {
      ids: ids
    }
  })
}

export const add = (row) => {
  return request({
    url: '/flow/actadmdatabasechangelog/add',
    method: 'post',
    data: row
  })
}

export const update = (row) => {
  return request({
    url: '/actadmdatabasechangelog/update',
    method: 'post',
    data: row
  })
}

