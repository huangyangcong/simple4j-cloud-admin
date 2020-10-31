package com.simple4j.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Sets;
import com.simple4j.system.entity.RoleMenu;
import com.simple4j.system.mapper.RoleMenuMapper;
import com.simple4j.system.request.MenuGrantRequest;
import com.simple4j.system.service.IRoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 服务实现类
 *
 * @author hyc
 */
@Service
@RequiredArgsConstructor
public class RoleMenuServiceImpl implements IRoleMenuService {

  private final RoleMenuMapper roleMenuMapper;

  @Override
  public Set<String> getPermission(Set<String> roleIds) {
    if (CollUtil.isEmpty(roleIds)) {
      return Sets.newHashSet();
    }
    return roleMenuMapper.permissions(roleIds);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean grant(MenuGrantRequest menuGrantRequest) {
    return grant(menuGrantRequest.getMenuIds(), menuGrantRequest.getRoleIds());
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean grant(Set<String> menuIds, Set<String> roleIds) {
    // 删除角色配置的菜单集合
    roleMenuMapper.physicsDelete(
        Wrappers.<RoleMenu>update().lambda().in(RoleMenu::getRoleId, roleIds));
    // 组装配置
    List<RoleMenu> roleMenus = new ArrayList<>();
    roleIds.forEach(
        roleId ->
            menuIds.forEach(
                menuId -> {
                  RoleMenu roleMenu = new RoleMenu();
                  roleMenu.setRoleId(roleId);
                  roleMenu.setMenuId(menuId);
                  roleMenus.add(roleMenu);
                }));
    // 新增配置
    return roleMenuMapper.saveBatch(roleMenus);
  }

  @Override
  public void removeByRoleIds(Set<String> roleIds) {
    if (CollUtil.isNotEmpty(roleIds)) {
      roleMenuMapper.physicsDelete(
          Wrappers.<RoleMenu>lambdaQuery().in(RoleMenu::getRoleId, roleIds));
    }
  }

  @Override
  public void removeByMenuIds(Set<String> menuIds) {
    if (CollUtil.isNotEmpty(menuIds)) {
      roleMenuMapper.physicsDelete(
          Wrappers.<RoleMenu>lambdaQuery().in(RoleMenu::getMenuId, menuIds));
    }
  }
}
