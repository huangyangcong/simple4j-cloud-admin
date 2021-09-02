package com.simple4j.auth.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.temp.SaTempUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
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

	public String authentication(AuthUser authUser, final String providerId, boolean autoSignUp) {

		//1 从第三方获取 Userinfo
		HttpServletRequest request = loginToken.getRequest();
		// 获取 encodeState, https://gitee.com/pcore/just-auth-spring-security-starter/issues/I22JC7
		final String encodeState = request.getParameter("state");

		//2 查询是否已经有第三方的授权记录, List 按 rank 排序, 直接取第一条记录
		String providerUserId = authUser.getUuid();
		List<UserConnection> connectionDataList = userConnectionService
			.queryConnectionByProviderIdAndProviderUserId(providerId, providerUserId);

		//3 获取 securityContext 中的 authenticationToken, 判断是否为本地登录用户(不含匿名用户)

		String loginId = null;
		boolean isLogin = StpUtil.isLogin();
		if (isLogin) {
			loginId = StpUtil.getLoginIdAsString();
		}

		boolean cacheWasUsed = false;
		//4.1 没有第三方登录记录, 自动注册 或 绑定 或 临时创建第三方登录用户
		if (CollectionUtils.isEmpty(connectionDataList)) {
			// 无本地用户登录, 注册和绑定
			if (!isLogin) {
				// 自动注册, https://gitee.com/pcore/just-auth-spring-security-starter/issues/I22KP3.
				if (autoSignUp) {
					// 自动注册到本地账户, 注册第三方授权登录信息到 user_connection 与 auth_token
					loginId = userConnectionService.signUp(authUser, providerId, encodeState);
					// 不支持自动注册, https://gitee.com/pcore/just-auth-spring-security-starter/issues/I22KP3.
				} else {
					// 创建临时用户的 userDetails, 再次获取通过 SecurityContextHolder.getContext().getAuthentication().getPrincipal()
					// @formatter:off
//					loginId = SaTempUtil.createToken()
//						// username = authUser.getUsername() + "_" + providerId + "_" + providerUserId
//						// 重新注册本地账号时按自己的业务逻辑进行命名
//						.username(authUser.getUsername() + "_" + providerId + "_" + providerUserId)
//						// 临时密码, 重新注册本地账号时按自己的业务逻辑进行设置
//						.password("{noop}" + temporaryUserPassword)
//						.authUser(authUser)
//						.encodeState(encodeState)
//						.disabled(false)
//						.accountExpired(false)
//						.accountLocked(false)
//						.credentialsExpired(false)
//						.authorities(AuthorityUtils.commaSeparatedStringToAuthorityList(temporaryUserAuthorities))
//						.build();
					// @formatter:on
				}
				// 本地用户已登录, 绑定
			} else {
				// 当 principal 为 UserDetails 类型是进行绑定操作.
				userConnectionService.binding(loginId, authUser, providerId);
			}
			//4.2 有第三方登录记录
		} else {
			UserConnection connectionData = null;
			// SecurityContextHolder 中有已认证用户
			// 本地登录用户 userId
			if (isLogin) {
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
					loginId = null;
					isLogin = false;
				}
			}

			// 第三方授权登录流程
			if (loginId == null) {
				// 扩展点, 待实现让用户选择哪一个本地账户登录, 这里直接取第一条记录.
				connectionData = connectionDataList.get(0);
				String userId = connectionData.getUserId();
				loginId = StpUtil.getLoginId(null);
				cacheWasUsed = true;
				if (loginId == null) {
					cacheWasUsed = false;
					StpUtil.login(userId);
					loginId = StpUtil.getLoginIdAsString();
				}
			}

			// 异步更新第三方授权登录用户信息与 token 信息, 异步更新执行失败再次进行同步更新.
			asyncUpdateUserConnectionAndToken(authUser, connectionData);
		}

		// 5 本地登录用户, 直接返回
		if (isLogin) {
			return loginId;
		}

		// 6 创建成功认证 token 并返回
		return StpUtil.getTokenValue();
	}


	/**
	 * 异步更新第三方授权登录用户信息与 token 信息, 异步更新执行失败再次进行同步更新.
	 *
	 * @param authUser       {@link AuthUser}
	 * @param userConnection {@link UserConnection}
	 */
	private void asyncUpdateUserConnectionAndToken(AuthUser authUser, UserConnection userConnection) {
		try {
			// 异步更新第三方授权登录用户信息与 token 信息, 拒绝策略为: CALLER_RUNS
			updateConnectionTaskExecutor.execute(
				() -> {
					try {
						userConnectionService.updateUserConnection(authUser, userConnection);
					} catch (Exception e) {
						String msg = String.format("异步更新第三方授权登录用户信息与 token 信息失败: AuthUser=%s, ConnectionData=%s, error=%s",
							JSONUtil.toJsonStr(authUser),
							JSONUtil.toJsonStr(userConnection),
							e.getMessage());
						log.error(msg, e);
					}
				});
		} catch (RejectedExecutionException | NullPointerException e) {
			log.error(String.format("异步更新第三方授权登录用户信息与 token 信息失败: %s, 再次同步更新", e.getMessage()), e);
			// 异步执行失败, 直接同步更新授权登录用户信息与 token 信息
			try {
				userConnectionService.updateUserConnection(authUser, userConnection);
			} catch (Exception ex) {
				String msg = String.format("同步更新第三方授权登录用户信息与 token 信息失败: AuthUser=%s, ConnectionData=%s, error=%s",
					JSONUtil.toJsonStr(authUser),
					JSONUtil.toJsonStr(userConnection),
					e.getMessage());
				log.error(msg, e);
			}
		}
	}
}
