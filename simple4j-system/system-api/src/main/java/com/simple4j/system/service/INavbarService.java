package com.simple4j.system.service;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.*;
import com.simple4j.system.response.NavbarDetailResponse;

import java.util.List;

/**
 * 服务类
 *
 * @author hyc
 * @since 2020-08-26
 */
public interface INavbarService {

	/**
	 * 详情
	 */
	NavbarDetailResponse detail(NavbarDetailRequest navbarDetailRequest);

	/**
	 * 列表
	 */
	List<NavbarDetailResponse> list(NavbarListRequest navbarListRequest);

	/**
	 * 自定义分页
	 */
	Page<NavbarDetailResponse> page(NavbarPageRequest navbarPageRequest);

	/**
	 * 新增
	 */
	boolean add(NavbarAddRequest navbarAddRequest);

	/**
	 * 修改
	 */
	boolean update(NavbarUpdateRequest navbarUpdateRequest);

	/**
	 * 新增或修改
	 */
	boolean addOrUpdate(NavbarAddOrUpdateRequest navbarAddOrUpdateRequest);

	/**
	 * 删除
	 */
	boolean remove(NavbarRemoveRequest navbarRemoveRequest);
}
