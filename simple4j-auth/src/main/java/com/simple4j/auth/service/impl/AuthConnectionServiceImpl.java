package com.simple4j.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simple4j.api.base.BusinessException;
import com.simple4j.auth.entity.AuthConnection;
import com.simple4j.auth.enums.ErrorCodeEnum;
import com.simple4j.auth.mapper.UserConnectionMapper;
import com.simple4j.auth.service.IAuthConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务类
 *
 * @author hyc
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthConnectionServiceImpl implements IAuthConnectionService {
	private final UserConnectionMapper userConnectionMapper;

	@Override
	public List<AuthConnection> queryConnectionByProviderIdAndProviderUserId(String providerId, String providerUserId) {
		return userConnectionMapper.selectList(new LambdaQueryWrapper<AuthConnection>()
			.eq(AuthConnection::getProviderUserId, providerUserId)
			.eq(AuthConnection::getProviderId, providerId)
		);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
	public void unbinding(@NonNull String userId, @NonNull String providerId, @NonNull String providerUserId) {
		// 用户未登录或不是当前用户
		if (!StpUtil.isLogin() || !StpUtil.getLoginId().equals(userId)) {
			log.warn("用户进行解绑操作时, 用户未登录或不是当前用户; userId: {}, providerId: {}, providerUserId: {}", userId, providerId, providerUserId);
			throw new BusinessException(ErrorCodeEnum.UN_BINDING_ERROR.getCode(), userId);
		}
		// 解除绑定(第三方)
		userConnectionMapper.delete(new LambdaQueryWrapper<AuthConnection>().eq(AuthConnection::getUserId, userId).eq(AuthConnection::getProviderId, providerId));
	}

	@Async
	@Override
	public void updateUserConnection(AuthConnection authConnection) {
		// 更新 connectionData
		userConnectionMapper.update(authConnection, new LambdaQueryWrapper<AuthConnection>()
			.eq(AuthConnection::getUserId, authConnection.getUserId())
			.eq(AuthConnection::getProviderId, authConnection.getProviderId())
			.eq(AuthConnection::getProviderUserId, authConnection.getProviderUserId()));
	}

	@Override
	public void saveAuthConnection(AuthConnection authConnection) {
		userConnectionMapper.save(authConnection);
	}
}
