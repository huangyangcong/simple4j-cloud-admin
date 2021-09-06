package com.simple4j.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simple4j.api.base.BusinessException;
import com.simple4j.auth.entity.AuthToken;
import com.simple4j.auth.entity.AuthConnection;
import com.simple4j.auth.enums.ErrorCodeEnum;
import com.simple4j.auth.mapper.UserConnectionMapper;
import com.simple4j.auth.service.IAuthTokenService;
import com.simple4j.auth.service.IUserConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthUser;
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
public class UserConnectionServiceImpl implements IUserConnectionService {
	private final UserConnectionMapper userConnectionMapper;
	private final IAuthTokenService tokenService;
	private static final long TIME_OUT = 1000;

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


	/**
	 * 添加到 第三方登录记录表
	 *
	 * @param providerId 第三方服务商
	 * @param authUser   authUser
	 * @param userId     本地账户用户 Id
	 * @param authToken  authToken
	 */
	private void addConnectionData(String providerId, AuthUser authUser, String userId, AuthToken authToken) {
		AuthConnection authConnection = buildUserConnection(providerId, userId, authUser, authToken);
		userConnectionMapper.save(authConnection);
	}

	private AuthConnection buildUserConnection(String providerId, String userId, AuthUser authUser, AuthToken authToken) {
		// @formatter:off
		return AuthConnection.builder()
			.userId(userId)
			.displayName(authUser.getUsername())
			.imageUrl(authUser.getAvatar())
			.profileUrl(authUser.getBlog())
			.providerId(providerId)
			.providerUserId(authUser.getUuid())
			.accessToken(authToken.getAccessToken())
			.tokenId(authToken.getId())
			.refreshToken(authToken.getRefreshToken())
			.expireTime(authToken.getExpireTime())
			.build();
		// @formatter:on
	}

	@Async
	@Override
	public void updateUserConnection(AuthUser authUser, AuthConnection data) {
		AuthConnection connectionData = null;
		try {
			// 获取 AuthTokenPo
			me.zhyd.oauth.model.AuthToken token = authUser.getToken();
			AuthToken authToken = AuthToken.convert(TIME_OUT, token, data.getProviderId());
			authToken.setId(data.getTokenId());

			// 获取最新的 ConnectionData
			connectionData = buildUserConnection(data.getProviderId(), data.getUserId(), authUser, authToken);
			connectionData.setUserId(data.getUserId());
			connectionData.setTokenId(data.getTokenId());

			// 更新 connectionData
			userConnectionMapper.update(connectionData, new LambdaQueryWrapper<AuthConnection>()
				.eq(AuthConnection::getUserId, connectionData.getUserId())
				.eq(AuthConnection::getProviderId, connectionData.getProviderId())
				.eq(AuthConnection::getProviderUserId, connectionData.getProviderUserId())
			);
			// 更新 AuthTokenPo
			tokenService.updateAuthToken(authToken);
		} catch (Exception e) {
			log.error("更新第三方用户信息异常: " + e.getMessage());
			throw new BusinessException(ErrorCodeEnum.UPDATE_CONNECTION_DATA_FAILURE.getCode(), connectionData, e);
		}
	}

	@Override
	public void saveAuthConnection(String providerId, String loginId, AuthUser authUser, AuthToken authToken) {

	}
}
