import request from '@/router/axios';
import {baseUrl} from '@/config/env';

export const getPage = (page_no, page_size, params) => {
	return request({
		url: baseUrl + '//code/page',
		method: 'post',
		data: {
...
	params,
		page_no
:
	page_no,
		page_size
:
	page_size
}
})
}
export const getPage = (params) => {
	return request({
		url: baseUrl + '//code/list',
		method: 'post',
		data: {
...
	params
}
})
}

export const getDetail = (id) => {
	return request({
		url: baseUrl + '//code/detail',
		method: 'post',
		data: {
			id: id
		}
	})
}

export const remove = (ids) => {
	return request({
		url: baseUrl + '//code/remove',
		method: 'post',
		data: {
			ids: ids
		}
	})
}

export const add = (row) => {
	return request({
		url: baseUrl + '//code/add',
		method: 'post',
		data: row
	})
}

export const update = (row) => {
	return request({
		url: baseUrl + '//code/update',
		method: 'post',
		data: row
	})
}

