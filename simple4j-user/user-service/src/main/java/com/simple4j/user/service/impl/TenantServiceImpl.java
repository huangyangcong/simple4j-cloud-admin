package com.simple4j.user.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4j.user.common.constant.CommonConstant;
import com.simple4j.user.common.util.SecurityUtils;
import com.simple4j.user.dao.DeptMapper;
import com.simple4j.user.dao.RoleMapper;
import com.simple4j.user.dao.TenantMapper;
import com.simple4j.user.service.ITenantService;
import lombok.AllArgsConstructor;
import com.simple4j.user.entity.Dept;
import com.simple4j.user.entity.Role;
import com.simple4j.user.entity.Tenant;
import com.simple4j.user.mapstruct.TenantMapStruct;
import com.simple4j.user.request.TenantAddOrUpdateRequest;
import com.simple4j.user.request.TenantDetailRequest;
import com.simple4j.user.request.TenantListRequest;
import com.simple4j.user.request.TenantPageRequest;
import com.simple4j.user.request.TenantRemoveRequest;
import com.simple4j.user.request.TenantUpdateRequest;
import com.simple4j.user.response.TenantDetailResponse;

import org.springframework.stereotype.Service;


/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@AllArgsConstructor
public class TenantServiceImpl implements ITenantService {

	private final RoleMapper roleMapper;
	private final DeptMapper deptMapper;

	private final TenantMapStruct tenantMapStruct;


	@Override
	public Tenant getByTenantId(String tenantId) {
		return getOne(Wrappers.<Tenant>query().lambda().eq(Tenant::getTenantId, tenantId));
	}

	private String getTenantId(List<String> codes) {
		String code = RandomUtil.randomNumbers(6);
		if (codes.contains(code)) {
			return getTenantId(codes);
		}
		return code;
	}

	@Override
	public TenantDetailResponse detail(TenantDetailRequest tenantDetailRequest) {
		Tenant detail = getOne(
			Wrappers.<Tenant>lambdaQuery().eq(Tenant::getId, tenantDetailRequest.getId()));
		return tenantMapStruct.toVo(detail);
	}

	@Override
	public List<TenantDetailResponse> list(TenantListRequest tenantListRequest) {
		LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.<Tenant>lambdaQuery()
			.eq(StrUtil.isNotEmpty(tenantListRequest.getTenantId()), Tenant::getTenantId,
				tenantListRequest.getTenantId())
			.eq(StrUtil.isNotEmpty(tenantListRequest.getContactNumber()),
				Tenant::getContactNumber,
				tenantListRequest.getContactNumber())
			.eq(StrUtil.isNotEmpty(tenantListRequest.getTenantName()), Tenant::getTenantName,
				tenantListRequest.getTenantName());
		List<Tenant> list = list(
			(!Objects.equals(SecurityUtils.getTenantId(), CommonConstant.ADMIN_TENANT_ID)) ? queryWrapper
				.eq(Tenant::getTenantId, SecurityUtils.getTenantId()) : queryWrapper);
		return tenantMapStruct.toVo(list);
	}

	@Override
	public Page<TenantDetailResponse> page(TenantPageRequest tenantPageRequest) {
		LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.<Tenant>lambdaQuery()
			.eq(StrUtil.isNotEmpty(tenantPageRequest.getTenantId()), Tenant::getTenantId,
				tenantPageRequest.getTenantId())
			.eq(StrUtil.isNotEmpty(tenantPageRequest.getContactNumber()), Tenant::getContactNumber,
				tenantPageRequest.getContactNumber())
			.eq(StrUtil.isNotEmpty(tenantPageRequest.getTenantName()), Tenant::getTenantName,
				tenantPageRequest.getTenantName());
		Page<Tenant> pages = page(
			new Page<>(tenantPageRequest.getPageNo(), tenantPageRequest.getPageSize()),
			(!Objects.equals(SecurityUtils.getTenantId(), CommonConstant.ADMIN_TENANT_ID)) ? queryWrapper
				.eq(Tenant::getTenantId, SecurityUtils.getTenantId()) : queryWrapper);
		return tenantMapStruct.toVo(pages);
	}

	@Override
	public void add(TenantAddOrUpdateRequest tenantAddRequest) {
		save(tenantMapStruct.toPo(tenantAddRequest));
	}

	@Override
	public void update(TenantUpdateRequest tenantUpdateRequest) {
		updateById(tenantMapStruct.toPo(tenantUpdateRequest));
	}

	@Override
	public void remove(TenantRemoveRequest tenantRemoveRequest) {
		removeByIds(tenantRemoveRequest.getIds());
	}

	@Override
	public void addOrUpdate(TenantAddOrUpdateRequest tenantAddOrUpdateRequest) {
		Tenant tenant = tenantMapStruct.toPo(tenantAddOrUpdateRequest);
		if (ObjectUtil.isEmpty(tenant.getId())) {
			List<Tenant> tenants = baseMapper
				.selectList(Wrappers.<Tenant>query().lambda().eq(Tenant::getIsDelete,
					CommonConstant.DB_NOT_DELETED));
			List<String> codes = tenants.stream().map(Tenant::getTenantId)
				.collect(Collectors.toList());
			String tenantId = getTenantId(codes);
			tenant.setTenantId(tenantId);
			// 新建租户对应的默认角色
			Role role = new Role();
			role.setTenantId(tenantId);
			role.setParentId(0L);
			role.setRoleName("管理员");
			role.setRoleAlias("admin");
			role.setSort(2);
			role.setIsDeleted(0);
			roleMapper.insert(role);
			// 新建租户对应的默认部门
			Dept dept = new Dept();
			dept.setTenantId(tenantId);
			dept.setParentId(0L);
			dept.setDeptName(tenant.getTenantName());
			dept.setFullName(tenant.getTenantName());
			dept.setSort(2);
			dept.setIsDeleted(0);
			deptMapper.insert(dept);
		}
		saveOrUpdate(tenant);
	}
}
