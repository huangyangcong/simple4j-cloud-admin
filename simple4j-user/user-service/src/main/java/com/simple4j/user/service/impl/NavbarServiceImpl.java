package com.simple4j.user.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4j.user.service.INavbarMenuService;
import com.simple4j.user.service.INavbarService;
import lombok.RequiredArgsConstructor;
import com.simple4j.user.entity.Navbar;
import com.simple4j.user.mapper.NavbarMapper;
import com.simple4j.user.mapstruct.NavbarMapStruct;
import com.simple4j.user.request.NavbarAddOrUpdateRequest;
import com.simple4j.user.request.NavbarAddRequest;
import com.simple4j.user.request.NavbarDetailRequest;
import com.simple4j.user.request.NavbarListRequest;
import com.simple4j.user.request.NavbarPageRequest;
import com.simple4j.user.request.NavbarRemoveRequest;
import com.simple4j.user.request.NavbarUpdateRequest;
import com.simple4j.user.response.NavbarDetailResponse;
import com.simple4j.user.service.INavbarMenuService;
import com.simple4j.user.service.INavbarService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 服务实现类
 *
 * @author Blade
 * @since 2020-08-26
 */
@Service
@RequiredArgsConstructor
public class NavbarServiceImpl extends ServiceImpl<NavbarMapper, Navbar> implements INavbarService {

	private final NavbarMapStruct navbarMapStruct;
	private final INavbarMenuService navbarMenuService;

	@Override
	public NavbarDetailResponse detail(NavbarDetailRequest navbarDetailRequest) {
		Navbar detail = getOne(
			Wrappers.<Navbar>lambdaQuery().eq(Navbar::getId, navbarDetailRequest.getId()));
		return navbarMapStruct.toVo(detail);
	}

	@Override
	public List<NavbarDetailResponse> list(NavbarListRequest navbarListRequest) {
		LambdaQueryWrapper<Navbar> queryWrapper = Wrappers.<Navbar>lambdaQuery()
			.eq(Navbar::getTenantId,
				navbarListRequest.getTenantId());
		List<Navbar> pages = list(queryWrapper);
		return navbarMapStruct.toVo(pages);
	}

	@Override
	public Page<NavbarDetailResponse> page(NavbarPageRequest navbarPageRequest) {
		LambdaQueryWrapper<Navbar> queryWrapper = Wrappers.<Navbar>lambdaQuery();
		Page<Navbar> pages = page(
			new Page<>(navbarPageRequest.getPageNo(), navbarPageRequest.getPageSize()),
			queryWrapper);
		return navbarMapStruct.toVo(pages);
	}

	@Override
	public void add(NavbarAddRequest navbarAddRequest) {
		save(navbarMapStruct.toPo(navbarAddRequest));
	}

	@Override
	public void update(NavbarUpdateRequest navbarUpdateRequest) {
		updateById(navbarMapStruct.toPo(navbarUpdateRequest));
	}

	@Override
	public void addOrUpdate(NavbarAddOrUpdateRequest navbarAddOrUpdateRequest) {
		saveOrUpdate(navbarMapStruct.toPo(navbarAddOrUpdateRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void remove(NavbarRemoveRequest navbarRemoveRequest) {
		removeByIds(navbarRemoveRequest.getIds());
	}
}
