package com.simple4j.user.service;

import java.util.List;

import com.simple4j.api.base.Page;
import com.simple4j.user.request.ClientAddOrUpdateRequest;
import com.simple4j.user.request.ClientAddRequest;
import com.simple4j.user.request.ClientDetailRequest;
import com.simple4j.user.request.ClientListRequest;
import com.simple4j.user.request.ClientPageRequest;
import com.simple4j.user.request.ClientRemoveRequest;
import com.simple4j.user.request.ClientUpdateRequest;
import com.simple4j.user.response.ClientDetailResponse;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IAuthClientService {

	/**
	 * 详情
	 */
	ClientDetailResponse detail(ClientDetailRequest clientDetailRequest);

	/**
	 * 列表 客户端表
	 */
	List<ClientDetailResponse> list(ClientListRequest clientListRequest);

	/**
	 * 自定义分页 客户端表
	 */
	Page<ClientDetailResponse> page(ClientPageRequest clientPageRequest);

	/**
	 * 新增 客户端表
	 */
	boolean add(ClientAddRequest clientAddRequest);

	/**
	 * 修改 客户端表
	 */
	boolean update(ClientUpdateRequest clientUpdateRequest);

	/**
	 * 新增或修改 客户端表
	 */
	boolean addOrUpdate(ClientAddOrUpdateRequest clientAddOrUpdateRequest);


	/**
	 * 删除 客户端表
	 */
	boolean remove(ClientRemoveRequest clientRemoveRequest);

}
