package com.simple4j.user.service;

import java.util.List;

import com.simple4j.user.base.Page;
import com.simple4j.user.request.UserOauthAddOrUpdateRequest;
import com.simple4j.user.request.UserOauthAddRequest;
import com.simple4j.user.request.UserOauthDetailRequest;
import com.simple4j.user.request.UserOauthListRequest;
import com.simple4j.user.request.UserOauthPageRequest;
import com.simple4j.user.request.UserOauthRemoveRequest;
import com.simple4j.user.request.UserOauthUpdateRequest;
import com.simple4j.user.response.UserOauthDetailResponse;


/**
 * 用户第三方认证表 服务类
 *
 * @author Blade
 * @since 2020-09-16
 */
public interface IUserOauthService {

	/**
	 * 详情
	 */
	UserOauthDetailResponse detail(UserOauthDetailRequest userOauthDetailRequest);

	/**
	 * 列表 用户第三方认证表
	 */
	List<UserOauthDetailResponse> list(UserOauthListRequest userOauthListRequest);

	/**
	 * 自定义分页 用户第三方认证表
	 */
	Page<UserOauthDetailResponse> page(UserOauthPageRequest userOauthPageRequest);

	/**
	 * 新增 用户第三方认证表
	 */
	boolean add(UserOauthAddRequest userOauthAddRequest);

	/**
	 * 修改 用户第三方认证表
	 */
	boolean update(UserOauthUpdateRequest userOauthUpdateRequest);

	/**
	 * 新增或修改 用户第三方认证表
	 */
	boolean addOrUpdate(UserOauthAddOrUpdateRequest userOauthAddOrUpdateRequest);


	/**
	 * 删除 用户第三方认证表
	 */
	boolean remove(UserOauthRemoveRequest userOauthRemoveRequest);
}
