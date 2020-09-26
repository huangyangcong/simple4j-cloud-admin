package com.simple4j.user.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.user.common.constant.CommonConstant;
import com.simple4j.user.mapper.RoleMenuMapper;
import com.simple4j.user.request.*;
import com.simple4j.user.util.SecurityUtils;
import com.simple4j.user.service.IDictService;
import com.simple4j.user.service.IMenuService;
import com.simple4j.user.service.IRoleMenuService;
import com.simple4j.user.util.TreeUtil;
import lombok.AllArgsConstructor;
import com.simple4j.user.dto.MenuDTO;
import com.simple4j.user.entity.Menu;
import com.simple4j.user.entity.RoleMenu;
import com.simple4j.user.mapper.MenuMapper;
import com.simple4j.user.mapstruct.MenuMapStruct;
import com.simple4j.user.response.MenuDetailResponse;
import com.simple4j.user.response.MenuRoutersResponse;
import com.simple4j.user.response.RoleMenuKeyResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 服务实现类
 *
 * @author Chill
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
		Menu detail = menuMapper.getOne(
			Wrappers.<Menu>lambdaQuery().eq(Menu::getId, menuDetailRequest.getId()));
		MenuDetailResponse menuDetailResponse = menuMapStruct.toVo(detail);
		if (menuDetailResponse.getParentId().equals(CommonConstant.TOP_PARENT_ID)) {
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
		////所有菜单
		//List<Menu> allMenus = baseMapper.allMenu();
		////角色菜单
		//List<Menu> roleMenus = baseMapper.roleMenu(roleIds);
		//List<Menu> routes = new LinkedList<>(roleMenus);
		//roleMenus.forEach(roleMenu -> recursion(allMenus, routes, roleMenu));
		//routes.sort(Comparator.comparing(Menu::getSort));
		//List<Menu> menus = routes.stream()
		//	.filter(x -> x.getCategory() == 1)
		//	.collect(Collectors.toList());
		Long navbarId = menuRoutersRequest.getNavbarId();
		List<Long> roleIds = SecurityUtils.isTenantAdmin() ? null :
				SecurityUtils.getCurrentUserDataRoles();
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
		//获取父级菜单
		Optional<Menu> menu = allMenus.stream().filter(x -> x.getId()
			.equals(roleMenu.getParentId())).findFirst();
		if (menu.isPresent() && !routes.contains(menu.get())) {
			routes.add(menu.get());
			recursion(allMenus, routes, menu.get());
		}
	}

	@Override
	public List<MenuDetailResponse> buttons() {
		List<Long> roleIds = SecurityUtils.getCurrentUserDataRoles();
		List<Menu> buttons = menuMapper.buttons(roleIds);
		return TreeUtil.buildTree(menuMapStruct.toVo(buttons));
	}

	@Override
	public List<MenuDetailResponse> tree() {
		return TreeUtil.buildTree(menuMapStruct.toVo(menuMapper.tree()));
	}

	@Override
	public List<MenuDetailResponse> grantTree() {
		return TreeUtil
			.buildTree(menuMapStruct.toVo(SecurityUtils.getTenantId().equals(CommonConstant.ADMIN_TENANT_ID) ?
					menuMapper.grantTree()
				: menuMapper.grantTreeByRole(SecurityUtils.getCurrentUserDataRoles())));
	}

	@Override
	public RoleMenuKeyResponse roleTreeKeys(RoleMenuKeyRequest roleMenuKeyRequest) {
		RoleMenuKeyResponse roleMenuKeyResponse = new RoleMenuKeyResponse();
		if (CollUtil.isEmpty(roleMenuKeyRequest.getRoles())) {
			return roleMenuKeyResponse;
		}
		List<RoleMenu> roleMenus = roleMenuMapper
				.list(Wrappers.<RoleMenu>query().lambda()
						.in(RoleMenu::getRoleId, roleMenuKeyRequest.getRoles()));

		roleMenuKeyResponse.setMenus(roleMenus.stream().map(roleMenu -> roleMenu.getMenuId())
				.collect(Collectors.toList()));
		return roleMenuKeyResponse;
	}

	@Override
	public List<MenuRoutersResponse> authRoutes() {
		if (SecurityUtils.getCurrentUserId() == null || SecurityUtils.getCurrentUserId() == 0L) {
			return null;
		}
		List<MenuDTO> routes = menuMapper.authRoutes(SecurityUtils.getCurrentUserDataRoles());
		return routes.stream().map(
			route -> new MenuRoutersResponse(route.getPath(), StrUtil.split(route.getAlias(), ',')))
			.collect(Collectors.toList());
	}

	@Override
	public List<MenuDetailResponse> list(MenuListRequest menuListRequest) {
		LambdaQueryWrapper<Menu> queryWrapper = Wrappers.<Menu>lambdaQuery()
			.eq(StrUtil.isNotEmpty(menuListRequest.getCode()), Menu::getCode,
				menuListRequest.getCode())
			.eq(StrUtil.isNotEmpty(menuListRequest.getName()), Menu::getName,
				menuListRequest.getName());
		List<Menu> list = menuMapper.list(queryWrapper.orderByAsc(Menu::getSort));
		return TreeUtil.buildTree(menuMapStruct.toVo(list));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean remove(MenuRemoveRequest menuRemoveRequest) {
		List<Long> menuIds = menuRemoveRequest.getMenuIds();
		menuMapper.physicsDeleteBatchByIds(menuIds);
		roleMenuService.removeByMenuIds(menuIds);
		return true;
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean addOrUpdate(MenuAddOrUpdateRequest menuAddOrUpdateRequest){
		return menuMapper.saveOrUpdate(menuMapStruct.toPo(menuAddOrUpdateRequest));
	}
}
