package com.simple4j.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.user.base.Page;
import com.simple4j.user.entity.UserOauth;
import com.simple4j.user.mapper.UserOauthMapper;
import com.simple4j.user.mapstruct.UserOauthMapStruct;
import com.simple4j.user.request.*;
import com.simple4j.user.response.UserOauthDetailResponse;
import com.simple4j.user.service.IUserOauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 用户第三方认证表 服务实现类
 *
 * @author Blade
 * @since 2020-09-16
 */
@Service
@RequiredArgsConstructor
public class UserOauthServiceImpl implements IUserOauthService {

	private final UserOauthMapStruct userOauthMapStruct;
	private final UserOauthMapper userOauthMapper;

	@Override
	public UserOauthDetailResponse detail(UserOauthDetailRequest userOauthDetailRequest) {
		UserOauth detail = userOauthMapper.getOne(
				Wrappers.<UserOauth>lambdaQuery().eq(UserOauth::getId, userOauthDetailRequest.getId()));
		return userOauthMapStruct.toVo(detail);
	}

	@Override
	public List<UserOauthDetailResponse> list(UserOauthListRequest userOauthListRequest) {
		LambdaQueryWrapper<UserOauth> queryWrapper = Wrappers.<UserOauth>lambdaQuery();
		List<UserOauth> list = userOauthMapper.list(queryWrapper);
		return userOauthMapStruct.toVo(list);
	}

	@Override
	public Page<UserOauthDetailResponse> page(UserOauthPageRequest userOauthPageRequest) {
		LambdaQueryWrapper<UserOauth> queryWrapper = Wrappers.<UserOauth>lambdaQuery();
		IPage<UserOauth> page = userOauthMapper.page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(userOauthPageRequest.getPageNo(), userOauthPageRequest.getPageSize()), queryWrapper);
		Page<UserOauth> pages = new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
		return userOauthMapStruct.toVo(pages);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean add(UserOauthAddRequest userOauthAddRequest) {
		return userOauthMapper.save(userOauthMapStruct.toPo(userOauthAddRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean update(UserOauthUpdateRequest userOauthUpdateRequest) {
		return userOauthMapper.updateByIdBool(userOauthMapStruct.toPo(userOauthUpdateRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean addOrUpdate(UserOauthAddOrUpdateRequest userOauthAddOrUpdateRequest) {
		return userOauthMapper.saveOrUpdate(userOauthMapStruct.toPo(userOauthAddOrUpdateRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean remove(UserOauthRemoveRequest userOauthRemoveRequest) {
		return userOauthMapper.physicsDeleteBatchByIdsBool(userOauthRemoveRequest.getIds());
	}
}
