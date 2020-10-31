package com.simple4j.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.api.base.Page;
import com.simple4j.autoconfigure.jwt.security.SecurityScope;
import com.simple4j.autoconfigure.jwt.security.SecurityUtils;
import com.simple4j.system.entity.Navbar;
import com.simple4j.system.mapper.NavbarMapper;
import com.simple4j.system.mapstruct.NavbarMapStruct;
import com.simple4j.system.request.NavbarAddOrUpdateRequest;
import com.simple4j.system.request.NavbarAddRequest;
import com.simple4j.system.request.NavbarDetailRequest;
import com.simple4j.system.request.NavbarListRequest;
import com.simple4j.system.request.NavbarPageRequest;
import com.simple4j.system.request.NavbarRemoveRequest;
import com.simple4j.system.request.NavbarUpdateRequest;
import com.simple4j.system.response.NavbarDetailResponse;
import com.simple4j.system.service.INavbarMenuService;
import com.simple4j.system.service.INavbarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 服务实现类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Service
@RequiredArgsConstructor
public class NavbarServiceImpl implements INavbarService {

	private final NavbarMapper navbarMapper;
	private final NavbarMapStruct navbarMapStruct;
	private final INavbarMenuService navbarMenuService;

	@Override
	public NavbarDetailResponse detail(NavbarDetailRequest navbarDetailRequest) {
		Navbar detail = navbarMapper.getOne(
			Wrappers.<Navbar>lambdaQuery().eq(Navbar::getId, navbarDetailRequest.getId()));
		return navbarMapStruct.toVo(detail);
	}

	@Override
	public List<NavbarDetailResponse> list(NavbarListRequest navbarListRequest) {
		SecurityScope securityScope = SecurityUtils.getAuthenticatedSecurityScope();
		LambdaQueryWrapper<Navbar> queryWrapper = Wrappers.<Navbar>lambdaQuery()
			.eq(Navbar::getTenantId,
				securityScope.getTenantId());
		List<Navbar> pages = navbarMapper.list(queryWrapper);
		return navbarMapStruct.toVo(pages);
	}

	@Override
	public Page<NavbarDetailResponse> page(NavbarPageRequest navbarPageRequest) {
		LambdaQueryWrapper<Navbar> queryWrapper = Wrappers.<Navbar>lambdaQuery();
		IPage<Navbar> page = navbarMapper.page(
			new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
				navbarPageRequest.getPageNo(), navbarPageRequest.getPageSize()),
			queryWrapper);
		Page<Navbar> pages = new Page<>(page.getCurrent(), page.getSize(), page.getTotal(),
			page.getRecords());
		return navbarMapStruct.toVo(pages);
	}

	@Override
	public boolean add(NavbarAddRequest navbarAddRequest) {
		SecurityScope securityScope = SecurityUtils.getAuthenticatedSecurityScope();
		Navbar navbar = navbarMapStruct.toPo(navbarAddRequest);
		navbar.setTenantId(securityScope.getTenantId());
		return navbarMapper.save(navbar);
	}

	@Override
	public boolean update(NavbarUpdateRequest navbarUpdateRequest) {
		SecurityScope securityScope = SecurityUtils.getAuthenticatedSecurityScope();
		Navbar navbar = navbarMapStruct.toPo(navbarUpdateRequest);
		navbar.setTenantId(securityScope.getTenantId());
		return navbarMapper.updateByIdBool(navbar);
	}

	@Override
	public boolean addOrUpdate(NavbarAddOrUpdateRequest navbarAddOrUpdateRequest) {
		SecurityScope securityScope = SecurityUtils.getAuthenticatedSecurityScope();
		Navbar navbar = navbarMapStruct.toPo(navbarAddOrUpdateRequest);
		navbar.setTenantId(securityScope.getTenantId());
		return navbarMapper.saveOrUpdate(navbar);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean remove(NavbarRemoveRequest navbarRemoveRequest) {
		return navbarMapper.removeByIds(navbarRemoveRequest.getIds());
	}
}
