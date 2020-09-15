package com.simple4j.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4j.autoconfigure.util.TreeUtil;
import com.simple4j.user.common.constant.CommonConstant;
import com.simple4j.user.common.util.SecurityUtils;
import com.simple4j.user.service.IDeptService;
import com.simple4j.user.service.IUserDeptService;
import lombok.RequiredArgsConstructor;
import com.simple4j.user.entity.Dept;
import com.simple4j.user.dao.DeptMapper;
import com.simple4j.user.mapstruct.DeptMapStruct;
import com.simple4j.user.request.DeptAddRequest;
import com.simple4j.user.request.DeptDetailRequest;
import com.simple4j.user.request.DeptListRequest;
import com.simple4j.user.request.DeptPageRequest;
import com.simple4j.user.request.DeptRemoveRequest;
import com.simple4j.user.request.DeptUpdateRequest;
import com.simple4j.user.response.DeptDetailResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements IDeptService {

	private final DeptMapStruct deptMapStruct;
	private final IUserDeptService userDeptService;

	@Override
	public List<DeptDetailResponse> tree(String tenantId) {
		List<Dept> depts = baseMapper.tree(tenantId);
		return TreeUtil.buildTree(deptMapStruct.toVo(depts));
	}

	@Override
	public List<Long> getDeptIds(String tenantId, List<String> deptNames) {
		List<Dept> deptList = baseMapper.selectList(
			Wrappers.<Dept>query().lambda().eq(Dept::getTenantId, tenantId)
				.in(Dept::getDeptName, deptNames));
		if (deptList != null && deptList.size() > 0) {
			return deptList.stream().map(Dept::getId).distinct()
				.collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public List<String> getDeptNames(Long userId) {
		return baseMapper.getDeptNames(userId);
	}

	@Override
	public DeptDetailResponse detail(DeptDetailRequest deptDetailRequest) {
		Dept detail = getOne(
			Wrappers.<Dept>lambdaQuery().eq(Dept::getId, deptDetailRequest.getId()));

		DeptDetailResponse deptDetailResponse = deptMapStruct.toVo(detail);
		if (deptDetailResponse.getParentId().equals(CommonConstant.TOP_PARENT_ID)) {
			deptDetailResponse.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Dept parent = getById(deptDetailResponse.getParentId());
			deptDetailResponse.setParentName(parent.getDeptName());
		}
		return deptDetailResponse;
	}

	@Override
	public List<DeptDetailResponse> list(DeptListRequest deptListRequest) {
		LambdaQueryWrapper<Dept> queryWrapper = Wrappers.<Dept>lambdaQuery();
		List<Dept> pages = list(queryWrapper);
		return deptMapStruct.toVo(pages);
	}

	@Override
	public Page<DeptDetailResponse> page(DeptPageRequest deptPageRequest) {
		LambdaQueryWrapper<Dept> queryWrapper = Wrappers.<Dept>lambdaQuery()
			.like(StrUtil.isNotEmpty(deptPageRequest.getDeptName()), Dept::getDeptName,
				deptPageRequest.getDeptName())
			.like(StrUtil.isNotEmpty(deptPageRequest.getFullName()), Dept::getFullName,
				deptPageRequest.getFullName());
		Page<Dept> list = page(
			new Page<>(deptPageRequest.getPageNo(), deptPageRequest.getPageSize()),
			!CommonConstant.ADMIN_TENANT_ID.equals(
				SecurityUtils.getTenantId()) ? queryWrapper
				.eq(Dept::getTenantId, SecurityUtils.getTenantId()) : queryWrapper);
		return deptMapStruct.toVo(list);
	}

	@Override
	public void add(DeptAddRequest deptAddRequest) {
		save(deptMapStruct.toPo(deptAddRequest));
	}

	@Override
	public void update(DeptUpdateRequest deptUpdateRequest) {
		updateById(deptMapStruct.toPo(deptUpdateRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void remove(DeptRemoveRequest deptRemoveRequest) {
		List<String> deptIds = deptRemoveRequest.getIds();
		baseMapper.physicsDeleteBatchByIds(deptIds);
		userDeptService.removeByDeptIds(deptIds);
	}
}
