package com.simple4j.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.api.base.Page;
import com.simple4j.system.entity.NavbarMenu;
import com.simple4j.system.mapper.NavbarMenuMapper;
import com.simple4j.system.mapstruct.NavbarMenuMapStruct;
import com.simple4j.system.request.*;
import com.simple4j.system.response.NavbarMenuDetailResponse;
import com.simple4j.system.response.NavbarPermissionResponse;
import com.simple4j.system.service.INavbarMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Service
@RequiredArgsConstructor
public class NavbarMenuServiceImpl implements INavbarMenuService {

	private final NavbarMenuMapStruct navbarMenuMapStruct;
	private final NavbarMenuMapper navbarMenuMapper;

	@Override
	public NavbarMenuDetailResponse detail(NavbarMenuDetailRequest navbarMenuDetailRequest) {
		NavbarMenu detail =
			navbarMenuMapper.getOne(
				Wrappers.<NavbarMenu>lambdaQuery()
					.eq(NavbarMenu::getId, navbarMenuDetailRequest.getId()));
		return navbarMenuMapStruct.toVo(detail);
	}

	@Override
	public List<NavbarMenuDetailResponse> list(NavbarMenuListRequest navbarMenuListRequest) {
		LambdaQueryWrapper<NavbarMenu> queryWrapper = Wrappers.<NavbarMenu>lambdaQuery();
		List<NavbarMenu> pages = navbarMenuMapper.list(queryWrapper);
		return navbarMenuMapStruct.toVo(pages);
	}

	@Override
	public Page<NavbarMenuDetailResponse> page(NavbarMenuPageRequest navbarMenuPageRequest) {
		LambdaQueryWrapper<NavbarMenu> queryWrapper = Wrappers.<NavbarMenu>lambdaQuery();
		IPage<NavbarMenu> page =
			navbarMenuMapper.page(
				new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
					navbarMenuPageRequest.getPageNo(), navbarMenuPageRequest.getPageSize()),
				queryWrapper);
		Page<NavbarMenu> pages =
			new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
		return navbarMenuMapStruct.toVo(pages);
	}

	@Override
	public boolean add(NavbarMenuAddRequest navbarMenuAddRequest) {
		return navbarMenuMapper.save(navbarMenuMapStruct.toPo(navbarMenuAddRequest));
	}

	@Override
	public boolean update(NavbarMenuUpdateRequest navbarMenuUpdateRequest) {
		return navbarMenuMapper.updateByIdBool(navbarMenuMapStruct.toPo(navbarMenuUpdateRequest));
	}

	@Override
	public boolean addOrUpdate(NavbarMenuAddOrUpdateRequest navbarMenuAddOrUpdateRequest) {
		return navbarMenuMapper
			.saveOrUpdate(navbarMenuMapStruct.toPo(navbarMenuAddOrUpdateRequest));
	}

	@Override
	public boolean remove(NavbarMenuRemoveRequest navbarMenuRemoveRequest) {
		return navbarMenuMapper.removeByIds(navbarMenuRemoveRequest.getIds());
	}

	@Override
	public NavbarPermissionResponse permission(NavbarPermissionRequest navbarPermissionRequest) {
		NavbarPermissionResponse navbarPermissionResponse = new NavbarPermissionResponse();
		navbarPermissionResponse.setMenuIds(
			navbarMenuMapper
				.list(
					Wrappers.<NavbarMenu>lambdaQuery()
						.eq(NavbarMenu::getNavbarId, navbarPermissionRequest.getId()))
				.stream()
				.map(NavbarMenu::getMenuId)
				.collect(Collectors.toSet()));
		return navbarPermissionResponse;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void grant(NavbarGrantRequest navbarGrantRequest) {
		grant(navbarGrantRequest.getNavbarId(), navbarGrantRequest.getMenuIds());
	}

	@Override
	public void grant(String navbarId, Set<String> menuIds) {
		if (navbarId != null) {
			navbarMenuMapper.physicsDelete(
				Wrappers.<NavbarMenu>lambdaQuery().eq(NavbarMenu::getNavbarId, navbarId));
			if (CollUtil.isNotEmpty(menuIds)) {
				List<NavbarMenu> navbarMenus = new ArrayList<>();
				for (String menuId : menuIds) {
					NavbarMenu navbarMenu = new NavbarMenu();
					navbarMenu.setNavbarId(navbarId);
					navbarMenu.setMenuId(menuId);
					navbarMenus.add(navbarMenu);
				}
				navbarMenuMapper.saveBatch(navbarMenus);
			}
		}
	}
}
