package com.simple4j.auth.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.auth.entity.User;
import com.simple4j.auth.entity.UserConnection;
import com.simple4j.auth.exceptions.UserNotFoundException;
import com.simple4j.auth.mapper.UserMapper;
import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.response.UserLoginResponse;
import com.simple4j.auth.service.IAuthTokenService;
import com.simple4j.auth.service.ICaptchaService;
import com.simple4j.auth.service.IUserConnectionService;
import com.simple4j.auth.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/**
 * 服务实现类
 *
 * @author hyc
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
	private final UserMapper userMapper;
	private final ICaptchaService captchaService;
	private final IAuthTokenService authTokenService;
	private final IUserConnectionService userConnectionService;
	private final ExecutorService updateConnectionTaskExecutor;

	@Override
	public void registerUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public UserLoginResponse login(UserLoginRequest userLoginRequest) {
		String username = userLoginRequest.getUsername();
		User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getAccount, username));
		if (ObjectUtil.isEmpty(user)) {
			throw new UserNotFoundException();
		}
		String password = user.getPassword();
		if (!password.equals(userLoginRequest.getPassword())) {
			throw new UserNotFoundException();
		}
		String captchaKey = userLoginRequest.getCaptchaKey();
		// 校验验证码
		captchaService.verify(username, captchaKey, userLoginRequest.getCaptchaCode());
		String id = user.getId();
		StpUtil.login(id);
		String token = StpUtil.getTokenValueByLoginId(id);
		return new UserLoginResponse(token);
	}

	public SaSession authentication(AuthUser authUser, final String providerId, boolean autoSignUp) {

		//1 从第三方获取 Userinfo
		HttpServletRequest request = loginToken.getRequest();
		// 获取 encodeState, https://gitee.com/pcore/just-auth-spring-security-starter/issues/I22JC7
		final String encodeState = request.getParameter("state");

		//2 查询是否已经有第三方的授权记录, List 按 rank 排序, 直接取第一条记录
		String providerUserId = authUser.getUuid();
		List<UserConnection> connectionDataList = userConnectionService
			.queryConnectionByProviderIdAndProviderUserId(providerId, providerUserId);

		//3 获取 securityContext 中的 authenticationToken, 判断是否为本地登录用户(不含匿名用户)

		SaSession saSession = null;
		if (StpUtil.isLogin()) {
			saSession = StpUtil.getSession();
		}

		boolean cacheWasUsed = false;
		//4.1 没有第三方登录记录, 自动注册 或 绑定 或 临时创建第三方登录用户
		if (CollectionUtils.isEmpty(connectionDataList)) {
			// 无本地用户登录, 注册和绑定
			if (saSession == null) {
				// 自动注册, https://gitee.com/pcore/just-auth-spring-security-starter/issues/I22KP3.
				if (autoSignUp) {
					// 自动注册到本地账户, 注册第三方授权登录信息到 user_connection 与 auth_token
					saSession = userConnectionService.signUp(authUser, providerId, encodeState);
				}
				// 不支持自动注册, https://gitee.com/pcore/just-auth-spring-security-starter/issues/I22KP3.
				else {
					// 创建临时用户的 userDetails, 再次获取通过 SecurityContextHolder.getContext().getAuthentication().getPrincipal()
					// @formatter:off
					saSession = TemporaryUser.builder()
						// username = authUser.getUsername() + "_" + providerId + "_" + providerUserId
						// 重新注册本地账号时按自己的业务逻辑进行命名
						.username(authUser.getUsername() + "_" + providerId + "_" + providerUserId)
						// 临时密码, 重新注册本地账号时按自己的业务逻辑进行设置
						.password("{noop}" + temporaryUserPassword)
						.authUser(authUser)
						.encodeState(encodeState)
						.disabled(false)
						.accountExpired(false)
						.accountLocked(false)
						.credentialsExpired(false)
						.authorities(AuthorityUtils.commaSeparatedStringToAuthorityList(temporaryUserAuthorities))
						.build();
					// @formatter:on
				}
			}
			// 本地用户已登录, 绑定
			else {
				if (StpUtil.isLogin()) {
					// 当 principal 为 UserDetails 类型是进行绑定操作.
					userConnectionService.binding(saSession, authUser, providerId);
					return saSession;
				}
				throw new InternalAuthenticationServiceException("principal is TemporaryUser or not UserDetails");

			}
		}
		//4.2 有第三方登录记录
		else {
			UserConnection connectionData = null;
			// SecurityContextHolder 中有已认证用户
			// 本地登录用户 userId
			if (StpUtil.isLogin()) {
				String userId = StpUtil.getLoginIdAsString();
				for (UserConnection data : connectionDataList) {
					if (userId.equals(data.getUserId())) {
						// 与本地登录的 userId 相同, 跳过第三方授权登录流程
						connectionData = data;
						break;
					}
				}

				// 与本地登录的 userId 不同
				if (connectionData == null) {
					// 走第三方授权登录流程
					saSession = null;
				}
			}


			// 第三方授权登录流程
			if (saSession == null) {
				// 扩展点, 待实现让用户选择哪一个本地账户登录, 这里直接取第一条记录.
				connectionData = connectionDataList.get(0);
				String userId = connectionData.getUserId();
				saSession = StpUtil.getSessionByLoginId(userId);
				cacheWasUsed = true;
				if (saSession == null) {
					cacheWasUsed = false;
					saSession = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
				}
			}

			// 异步更新第三方授权登录用户信息与 token 信息, 异步更新执行失败再次进行同步更新.
			asyncUpdateUserConnectionAndToken(authUser, connectionData);
		}

		// 5 删除 session 中的 state 缓存
		Auth2DefaultRequest.removeStateCacheOfSessionCache(auth2DefaultRequest.getAuthStateCache(),
			auth2DefaultRequest.getAuthSource());
		// 6 本地登录用户, 直接返回
		if (saSession != null) {
			return saSession;
		}

		// 认证成功后前置与后置检查
		try {
			preAuthenticationChecks.check(saSession);
			additionalAuthenticationChecks(saSession, (Auth2LoginAuthenticationToken) authentication);
		} catch (AuthenticationException exception) {
			if (cacheWasUsed) {
				// There was a problem, so try again after checking
				// we're using latest data (i.e. not from the cache)
				cacheWasUsed = false;
				userDetails = umsUserDetailsService.loadUserByUserId(saSession.getUsername());
				preAuthenticationChecks.check(saSession);
				additionalAuthenticationChecks(saSession, (Auth2LoginAuthenticationToken) authentication);
			} else {
				throw exception;
			}
		}

		postAuthenticationChecks.check(saSession);

		// 放入缓存
		if (!cacheWasUsed) {
			this.userCache.putUserInCache(saSession);
		}

		// 7 创建成功认证 token 并返回
		return saSession;
	}


	/**
	 * 异步更新第三方授权登录用户信息与 token 信息, 异步更新执行失败再次进行同步更新.
	 *
	 * @param authUser       {@link AuthUser}
	 * @param connectionData {@link ConnectionData}
	 */
	private void asyncUpdateUserConnectionAndToken(AuthUser authUser, UserConnection connectionData) {
		try {
			// 异步更新第三方授权登录用户信息与 token 信息, 拒绝策略为: CALLER_RUNS
			updateConnectionTaskExecutor.execute(
				() -> {
					try {
						userConnectionService.updateUserConnection(authUser, connectionData);
					} catch (Exception e) {
						String msg = String.format("异步更新第三方授权登录用户信息与 token 信息失败: AuthUser=%s, ConnectionData=%s, error=%s",
							toJsonString(authUser),
							toJsonString(connectionData),
							e.getMessage());
						log.error(msg, e);
					}
				});
		} catch (RejectedExecutionException | NullPointerException e) {
			log.error(String.format("异步更新第三方授权登录用户信息与 token 信息失败: %s, 再次同步更新", e.getMessage()), e);
			// 异步执行失败, 直接同步更新授权登录用户信息与 token 信息
			try {
				userConnectionService.updateUserConnection(authUser, connectionData);
			} catch (Exception ex) {
				String msg = String.format("同步更新第三方授权登录用户信息与 token 信息失败: AuthUser=%s, ConnectionData=%s, error=%s",
					toJsonString(authUser),
					toJsonString(connectionData),
					e.getMessage());
				log.error(msg, e);
			}
		}
	}
}
