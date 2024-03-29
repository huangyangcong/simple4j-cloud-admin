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
import com.simple4j.system.entity.Dept;
import com.simple4j.system.mapper.DeptMapper;
import com.simple4j.system.mapstruct.DeptMapStruct;
import com.simple4j.system.request.*;
import com.simple4j.system.response.DeptDetailResponse;
import com.simple4j.system.service.IDeptService;
import com.simple4j.system.service.IUserDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author hyc
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements IDeptService {

	private final DeptMapper deptMapper;
	private final DeptMapStruct deptMapStruct;
	private final IUserDeptService userDeptService;

	@Override
	public List<DeptDetailResponse> tree(String tenantId) {
		SecurityScope securityScope = SecurityUtils.getAuthenticatedSecurityScope();
		List<Dept> depts =
			deptMapper.tree(StrUtil.nullToDefault(tenantId, securityScope.getTenantId()));
		return TreeUtil.buildTree(deptMapStruct.toVo(depts));
	}

	@Override
	public Set<String> getDeptIds(String tenantId, List<String> deptNames) {
		List<Dept> deptList =
			deptMapper.selectList(
				Wrappers.<Dept>query()
					.lambda()
					.eq(Dept::getTenantId, tenantId)
					.in(Dept::getDeptName, deptNames));
		if (CollUtil.isNotEmpty(deptList)) {
			return deptList.stream().map(Dept::getId).collect(Collectors.toSet());
		}
		return null;
	}

	@Override
	public List<String> getDeptNames(String userId) {
		return deptMapper.getDeptNames(userId);
	}

	@Override
	public DeptDetailResponse detail(DeptDetailRequest deptDetailRequest) {
		Dept detail =
			deptMapper
				.getOne(Wrappers.<Dept>lambdaQuery().eq(Dept::getId, deptDetailRequest.getId()));

		DeptDetailResponse deptDetailResponse = deptMapStruct.toVo(detail);
		if (deptDetailResponse.getParentId().equals(CommonConstant.TOP_PARENT_ID)) {
			deptDetailResponse.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Dept parent = deptMapper.getById(deptDetailResponse.getParentId());
			deptDetailResponse.setParentName(parent.getDeptName());
		}
		return deptDetailResponse;
	}

	@Override
	public List<DeptDetailResponse> list(DeptListRequest deptListRequest) {
		LambdaQueryWrapper<Dept> queryWrapper = Wrappers.lambdaQuery();
		List<Dept> pages = deptMapper.list(queryWrapper);
		return deptMapStruct.toVo(pages);
	}

	@Override
	public Page<DeptDetailResponse> page(DeptPageRequest deptPageRequest) {
		LambdaQueryWrapper<Dept> queryWrapper =
			Wrappers.<Dept>lambdaQuery()
				.like(
					StrUtil.isNotEmpty(deptPageRequest.getDeptName()),
					Dept::getDeptName,
					deptPageRequest.getDeptName())
				.like(
					StrUtil.isNotEmpty(deptPageRequest.getFullName()),
					Dept::getFullName,
					deptPageRequest.getFullName());
		SecurityScope securityScope = SecurityUtils.getAuthenticatedSecurityScope();
		IPage<Dept> page =
			deptMapper.page(
				new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
					deptPageRequest.getPageNo(), deptPageRequest.getPageSize()),
				!CommonConstant.ADMIN_TENANT_ID.equals(SecurityUtils.getCurrentTenantId())
					? queryWrapper.eq(Dept::getTenantId, securityScope.getTenantId())
					: queryWrapper);
		Page<Dept> pages =
			new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
		return deptMapStruct.toVo(pages);
	}

	@Override
	public boolean add(DeptAddRequest deptAddRequest) {
		return deptMapper.save(deptMapStruct.toPo(deptAddRequest));
	}

	@Override
	public boolean update(DeptUpdateRequest deptUpdateRequest) {
		return deptMapper.updateByIdBool(deptMapStruct.toPo(deptUpdateRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean remove(DeptRemoveRequest deptRemoveRequest) {
		Set<String> deptIds = deptRemoveRequest.getIds();
		deptMapper.physicsDeleteBatchByIds(deptIds);
		userDeptService.removeByDeptIds(deptIds);
		return true;
	}
}
