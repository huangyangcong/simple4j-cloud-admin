package com.simple4j.user.service.impl;

import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4j.user.service.IAuthClientService;
import lombok.RequiredArgsConstructor;
import com.simple4j.user.entity.AuthClient;
import com.simple4j.user.mapper.AuthClientMapper;
import com.simple4j.user.mapstruct.ClientMapStruct;
import com.simple4j.user.request.ClientAddOrUpdateRequest;
import com.simple4j.user.request.ClientAddRequest;
import com.simple4j.user.request.ClientDetailRequest;
import com.simple4j.user.request.ClientListRequest;
import com.simple4j.user.request.ClientPageRequest;
import com.simple4j.user.request.ClientRemoveRequest;
import com.simple4j.user.request.ClientUpdateRequest;
import com.simple4j.user.response.ClientDetailResponse;
import com.simple4j.user.service.IAuthClientService;

import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@RequiredArgsConstructor
public class AuthClientServiceImpl extends ServiceImpl<AuthClientMapper, AuthClient> implements
		IAuthClientService {

	private final ClientMapStruct clientMapStruct;

	@Override
	public ClientDetailResponse detail(ClientDetailRequest clientDetailRequest) {
		AuthClient detail = getOne(
			Wrappers.<AuthClient>lambdaQuery().eq(AuthClient::getId, clientDetailRequest.getId()));
		return clientMapStruct.toVo(detail);
	}

	@Override
	public List<ClientDetailResponse> list(ClientListRequest clientListRequest) {
		LambdaQueryWrapper<AuthClient> queryWrapper = Wrappers.lambdaQuery();
		List<AuthClient> pages = list(queryWrapper);
		return clientMapStruct.toVo(pages);
	}

	@Override
	public Page<ClientDetailResponse> page(ClientPageRequest clientPageRequest) {
		Page<AuthClient> pages = page(
			new Page<>(clientPageRequest.getPageNo(), clientPageRequest.getPageSize()),
			Wrappers.<AuthClient>lambdaQuery()
				.eq(StrUtil.isNotEmpty(clientPageRequest.getClientId()), AuthClient::getClientId,
					clientPageRequest.getClientId())
				.eq(StrUtil.isNotEmpty(clientPageRequest.getClientSecret()),
					AuthClient::getClientSecret,
					clientPageRequest.getClientSecret()));
		return clientMapStruct.toVo(pages);
	}

	@Override
	public void add(ClientAddRequest clientAddRequest) {
		save(clientMapStruct.toPo(clientAddRequest));
	}

	@Override
	public void update(ClientUpdateRequest clientUpdateRequest) {
		updateById(clientMapStruct.toPo(clientUpdateRequest));
	}

	@Override
	public void addOrUpdate(ClientAddOrUpdateRequest clientAddOrUpdateRequest) {
		saveOrUpdate(clientMapStruct.toPo(clientAddOrUpdateRequest));
	}

	@Override
	public void remove(ClientRemoveRequest clientRemoveRequest) {
		removeByIds(clientRemoveRequest.getIds());
	}
}
