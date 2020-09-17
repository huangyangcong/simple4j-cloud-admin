package com.simple4j.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.api.base.Page;
import com.simple4j.user.common.constant.CommonConstant;
import com.simple4j.user.request.*;
import com.simple4j.user.util.SecurityUtils;
import com.simple4j.user.mapper.RoleMapper;
import com.simple4j.user.entity.Role;
import com.simple4j.user.mapstruct.RoleMapStruct;
import com.simple4j.user.response.RoleDetailResponse;
import com.simple4j.user.service.IRoleMenuService;
import com.simple4j.user.service.IRoleService;
import com.simple4j.user.util.TreeUtil;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@Validated
@AllArgsConstructor
public class RoleServiceImpl implements IRoleService {

	private final RoleMapper roleMapper;
	private final IRoleMenuService roleMenuService;
	private final RoleMapStruct roleMapStruct;

	@Override
	public RoleDetailResponse detail(
		RoleDetailRequest roleDetailRequest) {
		Role detail = roleMapper.getOne(
			Wrappers.<Role>lambdaQuery().eq(Role::getId, roleDetailRequest.getId()));
		RoleDetailResponse roleDetailResponse = roleMapStruct.toVo(detail);
		if (roleDetailResponse.getParentId().equals(CommonConstant.TOP_PARENT_ID)) {
			roleDetailResponse.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Role parent = roleMapper.getById(roleDetailResponse.getParentId());
			roleDetailResponse.setParentName(parent.getRoleName());
		}
		return roleDetailResponse;
	}

	@Override
	public Page<RoleDetailResponse> page(RolePageRequest rolePageRequest) {
		LambdaQueryWrapper<Role> queryWrapper = Wrappers.<Role>lambdaQuery();
		IPage<Role> page = roleMapper.page(
			new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(rolePageRequest.getPageNo(), rolePageRequest.getPageSize()), queryWrapper);
		Page<Role> pages = new Page<>(page.getCurrent(), page.getSize(), page.getTotal(),
				page.getRecords());
		return roleMapStruct.toVo(pages);
	}

	@Override
	public List<RoleDetailResponse> tree(String tenantId) {
		return TreeUtil.buildTree(roleMapStruct.toVo(roleMapper.tree(StrUtil.nullToDefault(tenantId,
				SecurityUtils.getTenantId()), null)));
	}

	@Override
	public List<RoleDetailResponse> list(RoleListRequest roleListRequest) {
		LambdaQueryWrapper<Role> queryWrapper = Wrappers.<Role>lambdaQuery()
			.eq(StrUtil.isNotEmpty(roleListRequest.getRoleName()), Role::getRoleName,
				roleListRequest.getRoleName())
			.eq(StrUtil.isNotEmpty(roleListRequest.getRoleAlias()), Role::getRoleAlias,
				roleListRequest.getRoleAlias());
		List<Role> pages = roleMapper.list(
			(!SecurityUtils.getTenantId().equals(CommonConstant.ADMIN_TENANT_ID)) ? queryWrapper
				.eq(Role::getTenantId, SecurityUtils.getTenantId()) : queryWrapper);
		;
		return roleMapStruct.toVo(pages);
	}

	@Override
	public List<Long> getRoleIds(String tenantId, List<String> roleNames) {
		List<Role> roleList = roleMapper.selectList(
			Wrappers.<Role>query().lambda().eq(Role::getTenantId, tenantId)
				.in(Role::getRoleName, roleNames));
		if (roleList != null && roleList.size() > 0) {
			return roleList.stream().map(Role::getId).distinct()
				.collect(Collectors.toList());
		}
		return null;
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean addOrUpdate(RoleAddOrUpdateRequest roleAddOrUpdateRequest){
		Role role = roleMapStruct.toPo(roleAddOrUpdateRequest);
		role.setTenantId(SecurityUtils.getTenantId());
		return roleMapper.saveOrUpdate(role);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean remove(RoleRemoveRequest roleRemoveRequest) {
		List<Long> roleIds = roleRemoveRequest.getRoleIds();
		roleMapper.physicsDeleteBatchByIds(roleIds);
		roleMenuService.removeByRoleIds(roleIds);
		return true;
	}

	@Override
	public List<String> getRoleNames(Long userId) {
		return roleMapper.getRoleNames(userId);
	}

	@Override
	public List<String> getRoleAlias(Long userId) {
		return roleMapper.getRoleAlias(userId);
	}
}
