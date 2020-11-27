package com.simple4j.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.api.util.TreeUtil;
import com.simple4j.autoconfigure.jwt.security.SecurityScope;
import com.simple4j.autoconfigure.jwt.security.SecurityUtils;
import com.simple4j.system.common.constant.CommonConstant;
import com.simple4j.system.dto.MenuDTO;
import com.simple4j.system.entity.Menu;
import com.simple4j.system.entity.RoleMenu;
import com.simple4j.system.mapper.MenuMapper;
import com.simple4j.system.mapper.RoleMenuMapper;
import com.simple4j.system.mapstruct.MenuMapStruct;
import com.simple4j.system.request.*;
import com.simple4j.system.response.MenuDetailResponse;
import com.simple4j.system.response.MenuRoutersResponse;
import com.simple4j.system.response.RoleMenuKeyResponse;
import com.simple4j.system.service.IDictService;
import com.simple4j.system.service.IMenuService;
import com.simple4j.system.service.IRoleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author hyc
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl implements IMenuService {

	private final IRoleMenuService roleMenuService;
	private final IDictService dictService;
	private final MenuMapStruct menuMapStruct;
	private final MenuMapper menuMapper;
	private final RoleMenuMapper roleMenuMapper;

	@Override
	public MenuDetailResponse detail(MenuDetailRequest menuDetailRequest) {
		Menu detail =
			menuMapper
				.getOne(Wrappers.<Menu>lambdaQuery().eq(Menu::getId, menuDetailRequest.getId()));
		MenuDetailResponse menuDetailResponse = menuMapStruct.toVo(detail);
		if (CommonConstant.TOP_PARENT_ID.equals(menuDetailResponse.getParentId())) {
			menuDetailResponse.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Menu parent = menuMapper.getById(menuDetailResponse.getParentId());
			menuDetailResponse.setParentName(parent.getName());
		}
		String d1 = dictService.getValue("menu_category", menuDetailResponse.getCategory());
		String d2 = dictService.getValue("button_func", menuDetailResponse.getAction());
		String d3 = dictService.getValue("yes_no", menuDetailResponse.getIsOpen());
		menuDetailResponse.setCategoryName(d1);
		menuDetailResponse.setActionName(d2);
		menuDetailResponse.setIsOpenName(d3);
		return menuDetailResponse;
	}

	@Override
	public List<MenuDetailResponse> routes(MenuRoutersRequest menuRoutersRequest) {
		//// 所有菜单
		// List<Menu> allMenus = baseMapper.allMenu();
		//// 角色菜单
		// List<Menu> roleMenus = baseMapper.roleMenu(roleIds);
		// List<Menu> routes = new LinkedList<>(roleMenus);
		// roleMenus.forEach(roleMenu -> recursion(allMenus, routes, roleMenu));
		// routes.sort(Comparator.comparing(Menu::getSort));
		// List<Menu> menus = routes.stream()
		//	.filter(x -> x.getCategory() == 1)
		//	.collect(Collectors.toList());
		SecurityScope securityScope = SecurityUtils.getCurrentSecurityScope();
		Long navbarId = menuRoutersRequest.getNavbarId();
		Set<String> roleIds =
			CommonConstant.ADMIN_TENANT_ID.equals(SecurityUtils.getCurrentTenantId())
				? null
				: securityScope.getRoleIds();
		List<Menu> menus = menuMapper.routes(navbarId, roleIds);
		return TreeUtil.buildTree(menuMapStruct.toVo(menus));
	}

	/**
	 * 添加父级菜单
	 *
	 * @param allMenus
	 * @param routes
	 * @param roleMenu
	 */
	public void recursion(List<Menu> allMenus, List<Menu> routes, Menu roleMenu) {
		// 获取父级菜单
		Optional<Menu> menu =
			allMenus.stream().filter(x -> x.getId().equals(roleMenu.getParentId())).findFirst();
		if (menu.isPresent() && !routes.contains(menu.get())) {
			routes.add(menu.get());
			recursion(allMenus, routes, menu.get());
		}
	}

	@Override
	public List<MenuDetailResponse> buttons() {
		SecurityScope securityScope = SecurityUtils.getCurrentSecurityScope();
		Set<String> roleIds = securityScope.getRoleIds();
		List<Menu> buttons = menuMapper.buttons(roleIds);
		return TreeUtil.buildTree(menuMapStruct.toVo(buttons));
	}

	@Override
	public List<MenuDetailResponse> tree() {
		return TreeUtil.buildTree(menuMapStruct.toVo(menuMapper.tree()));
	}

	@Override
	public List<MenuDetailResponse> grantTree() {
		return TreeUtil.buildTree(
			menuMapStruct.toVo(
				CommonConstant.ADMIN_TENANT_ID.equals(SecurityUtils.getCurrentTenantId())
					? menuMapper.grantTree()
					: menuMapper.grantTreeByRole(SecurityUtils.getCurrentRoleIds())));
	}

	@Override
	public RoleMenuKeyResponse roleTreeKeys(RoleMenuKeyRequest roleMenuKeyRequest) {
		RoleMenuKeyResponse roleMenuKeyResponse = new RoleMenuKeyResponse();
		if (CollUtil.isEmpty(roleMenuKeyRequest.getRoles())) {
			return roleMenuKeyResponse;
		}
		List<RoleMenu> roleMenus =
			roleMenuMapper.list(
				Wrappers.<RoleMenu>query()
					.lambda()
					.in(RoleMenu::getRoleId, roleMenuKeyRequest.getRoles()));

		roleMenuKeyResponse.setMenus(
			roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet()));
		return roleMenuKeyResponse;
	}

	@Override
	public List<MenuRoutersResponse> authRoutes() {
		SecurityScope securityScope = SecurityUtils.getCurrentSecurityScope();
		if (securityScope == null) {
			return Collections.emptyList();
		}
		List<MenuDTO> routes = menuMapper.authRoutes(securityScope.getRoleIds());
		return routes.stream()
			.map(
				route -> new MenuRoutersResponse(route.getPath(),
					StrUtil.split(route.getAlias(), ',')))
			.collect(Collectors.toList());
	}

	@Override
	public List<MenuDetailResponse> list(MenuListRequest menuListRequest) {
		LambdaQueryWrapper<Menu> queryWrapper =
			Wrappers.<Menu>lambdaQuery()
				.eq(
					StrUtil.isNotEmpty(menuListRequest.getCode()),
					Menu::getCode,
					menuListRequest.getCode())
				.eq(
					StrUtil.isNotEmpty(menuListRequest.getName()),
					Menu::getName,
					menuListRequest.getName());
		List<Menu> list = menuMapper.list(queryWrapper.orderByAsc(Menu::getSort));
		return TreeUtil.buildTree(menuMapStruct.toVo(list));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean remove(MenuRemoveRequest menuRemoveRequest) {
		Set<String> menuIds = menuRemoveRequest.getMenuIds();
		menuMapper.physicsDeleteBatchByIds(menuIds);
		roleMenuService.removeByMenuIds(menuIds);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean addOrUpdate(MenuAddOrUpdateRequest menuAddOrUpdateRequest) {
		return menuMapper.saveOrUpdate(menuMapStruct.toPo(menuAddOrUpdateRequest));
	}
}
