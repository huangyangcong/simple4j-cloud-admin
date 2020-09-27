package com.simple4j.system.service;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.ClientAddOrUpdateRequest;
import com.simple4j.system.request.ClientAddRequest;
import com.simple4j.system.request.ClientDetailRequest;
import com.simple4j.system.request.ClientListRequest;
import com.simple4j.system.request.ClientPageRequest;
import com.simple4j.system.request.ClientRemoveRequest;
import com.simple4j.system.request.ClientUpdateRequest;
import com.simple4j.system.response.ClientDetailResponse;

import java.util.List;

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
