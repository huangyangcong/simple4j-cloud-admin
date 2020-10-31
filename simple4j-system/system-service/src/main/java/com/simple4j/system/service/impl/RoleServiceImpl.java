package com.simple4j.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.api.base.Page;
import com.simple4j.api.util.TreeUtil;
import com.simple4j.autoconfigure.jwt.security.SecurityScope;
import com.simple4j.autoconfigure.jwt.security.SecurityUtils;
import com.simple4j.system.common.constant.CommonConstant;
import com.simple4j.system.entity.Role;
import com.simple4j.system.mapper.RoleMapper;
import com.simple4j.system.mapstruct.RoleMapStruct;
import com.simple4j.system.request.RoleAddOrUpdateRequest;
import com.simple4j.system.request.RoleDetailRequest;
import com.simple4j.system.request.RoleListRequest;
import com.simple4j.system.request.RolePageRequest;
import com.simple4j.system.request.RoleRemoveRequest;
import com.simple4j.system.response.RoleDetailResponse;
import com.simple4j.system.service.IRoleMenuService;
import com.simple4j.system.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author hyc
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
		SecurityScope securityScope = SecurityUtils.getAuthenticatedSecurityScope();
		return TreeUtil.buildTree(roleMapStruct.toVo(roleMapper.tree(StrUtil.nullToDefault(tenantId,
			securityScope.getTenantId()), null)));
	}

	@Override
	public List<RoleDetailResponse> list(RoleListRequest roleListRequest) {
		LambdaQueryWrapper<Role> queryWrapper = Wrappers.<Role>lambdaQuery()
			.eq(StrUtil.isNotEmpty(roleListRequest.getRoleName()), Role::getRoleName,
				roleListRequest.getRoleName())
			.eq(StrUtil.isNotEmpty(roleListRequest.getRoleAlias()), Role::getRoleAlias,
				roleListRequest.getRoleAlias());
		SecurityScope securityScope = SecurityUtils.getAuthenticatedSecurityScope();
		List<Role> pages = roleMapper.list(
			CommonConstant.ADMIN_TENANT_ID.equals(SecurityUtils.getCurrentTenantId()) ? queryWrapper
				.eq(Role::getTenantId, securityScope.getTenantId()) : queryWrapper);
		return roleMapStruct.toVo(pages);
	}

	@Override
	public Set<String> getRoleIds(String tenantId, List<String> roleNames) {
		List<Role> roleList = roleMapper.selectList(
			Wrappers.<Role>query().lambda().eq(Role::getTenantId, tenantId)
				.in(Role::getRoleName, roleNames));
		if (CollUtil.isNotEmpty(roleList)) {
			return roleList.stream().map(Role::getId)
				.collect(Collectors.toSet());
		}
		return null;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean addOrUpdate(RoleAddOrUpdateRequest roleAddOrUpdateRequest) {
		Role role = roleMapStruct.toPo(roleAddOrUpdateRequest);
		SecurityScope securityScope = SecurityUtils.getAuthenticatedSecurityScope();
		role.setTenantId(securityScope.getTenantId());
		return roleMapper.saveOrUpdate(role);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean remove(RoleRemoveRequest roleRemoveRequest) {
		Set<String> roleIds = roleRemoveRequest.getRoleIds();
		roleMapper.physicsDeleteBatchByIds(roleIds);
		roleMenuService.removeByRoleIds(roleIds);
		return true;
	}

	@Override
	public List<String> getRoleNames(String userId) {
		return roleMapper.getRoleNames(userId);
	}

	@Override
	public Set<String> getRoleAlias(String userId) {
		return roleMapper.getRoleAlias(userId);
	}
}
