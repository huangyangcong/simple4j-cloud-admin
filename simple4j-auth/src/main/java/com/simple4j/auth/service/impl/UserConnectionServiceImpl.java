package com.simple4j.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simple4j.api.base.BusinessException;
import com.simple4j.auth.entity.AuthToken;
import com.simple4j.auth.entity.UserConnection;
import com.simple4j.auth.enums.ErrorCodeEnum;
import com.simple4j.auth.exceptions.RegisterUserFailureException;
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
	public List<UserConnection> queryConnectionByProviderIdAndProviderUserId(String providerId, String providerUserId) {
		return userConnectionMapper.selectList(new LambdaQueryWrapper<UserConnection>()
			.eq(UserConnection::getProviderUserId, providerId)
			.eq(UserConnection::getUserId, providerUserId)
		);
	}


	@Override
	public void binding(String loginId, AuthUser authUser, String providerId) {
		// 第三方授权登录信息绑定到本地账号, 且添加第三方授权登录信息到 user_connection 与 auth_token
		registerConnection(providerId, authUser, loginId);
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
		userConnectionMapper.delete(new LambdaQueryWrapper<UserConnection>().eq(UserConnection::getUserId, userId).eq(UserConnection::getProviderId, providerId));
	}

	/**
	 * 第三方授权登录信息绑定到本地账号, 且添加第三方授权登录信息到 user_connection 与 auth_token
	 *
	 * @param providerId 第三方服务商
	 * @param authUser   {@link AuthUser}
	 * @throws RegisterUserFailureException 注册失败
	 */
	@Override
	public void registerConnection(String providerId, AuthUser authUser, String loginId) throws RegisterUserFailureException {

		// 注册第三方授权登录信息到 user_connection 与 auth_token
		me.zhyd.oauth.model.AuthToken token = authUser.getToken();
		// 有效期转时间戳
		AuthToken authToken = AuthToken.convert(TIME_OUT, token, providerId);
		try {
			// 添加 token
			tokenService.saveAuthToken(authToken);

			// 添加到 第三方登录记录表
			addConnectionData(providerId, authUser, loginId, authToken);
		} catch (Exception e) {
			String msg;
			if (authToken.getId() == null) {
				try {
					// 再次添加 token
					tokenService.saveAuthToken(authToken);
					// 再次添加到 第三方登录记录表
					addConnectionData(providerId, authUser, loginId, authToken);
				} catch (Exception ex) {
					msg = String.format("第三方授权登录自动注册时: 本地账户注册成功, %s, 添加第三方授权登录信息失败: %s",
						loginId, JSONUtil.toJsonStr(authUser));
					log.error(msg, e);
					throw new RegisterUserFailureException(ErrorCodeEnum.USER_REGISTER_OAUTH2_FAILURE,
						loginId, ex);
				}
			} else {
				try {
					// authToken 保存成功, authUser保存失败, 再次添加到 第三方登录记录表
					addConnectionData(providerId, authUser, loginId, authToken);
				} catch (Exception exception) {
					msg = String.format("第三方授权登录自动注册时: 本地账户注册成功, %s, 添加第三方授权登录信息失败: %s, 但 AuthToken 能成功执行 sql, 但已回滚: " +
							"%s",
						loginId,
						authUser.getRawUserInfo(),
						JSONUtil.toJsonStr(authToken));
					log.error(msg, e);
					throw new RegisterUserFailureException(ErrorCodeEnum.USER_REGISTER_OAUTH2_FAILURE,
						loginId);
				}
			}

		}

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
		UserConnection userConnection = buildUserConnection(providerId, userId, authUser, authToken);
		userConnectionMapper.save(userConnection);
	}

	private UserConnection buildUserConnection(String providerId, String userId, AuthUser authUser, AuthToken authToken) {
		// @formatter:off
		return UserConnection.builder()
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
	public void updateUserConnection(AuthUser authUser, UserConnection data) {
		UserConnection connectionData = null;
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
			userConnectionMapper.update(connectionData, new LambdaQueryWrapper<UserConnection>()
				.eq(UserConnection::getUserId, connectionData.getUserId())
				.eq(UserConnection::getProviderId, connectionData.getProviderId())
				.eq(UserConnection::getProviderUserId, connectionData.getProviderUserId())
			);
			// 更新 AuthTokenPo
			tokenService.updateAuthToken(authToken);
		} catch (Exception e) {
			log.error("更新第三方用户信息异常: " + e.getMessage());
			throw new BusinessException(ErrorCodeEnum.UPDATE_CONNECTION_DATA_FAILURE.getCode(), connectionData, e);
		}
	}
}
