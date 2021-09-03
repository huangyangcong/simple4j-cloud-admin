package com.simple4j.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.temp.SaTempUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.auth.entity.User;
import com.simple4j.auth.entity.UserConnection;
import com.simple4j.auth.exceptions.RegisterUserFailureException;
import com.simple4j.auth.exceptions.UserNotFoundException;
import com.simple4j.auth.mapper.UserMapper;
import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.response.UserLoginResponse;
import com.simple4j.auth.service.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

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
	private final IUserRoleService userRoleService;
	private final ICaptchaService captchaService;
	private final IAuthTokenService authTokenService;
	private final IUserConnectionService userConnectionService;
	private final ExecutorService updateConnectionTaskExecutor;

	@Override
	public String authentication(AuthUser authUser, String encodeState, String providerId, boolean autoSignUp) {
		return null;
	}

	@Override
	public String registerUser(AuthUser authUser, String username, String decodeState) {

		// 第三方授权登录不需要密码, 这里随便设置的, 生成环境按自己的逻辑
		String encodedPassword = passwordEncoder.encode(authUser.getUuid());

		// 这里的 decodeState 可以根据自己实现的 top.dcenter.ums.security.core.oauth.service.Auth2StateCoder 接口的逻辑来传递必要的参数.
		// 比如: 第三方登录成功后的跳转地址
		final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		// 假设 decodeState 就是 redirectUrl, 我们直接把 redirectUrl 设置到 request 上
		// 后续经过成功处理器时直接从 requestAttributes.getAttribute("redirectUrl", RequestAttributes.SCOPE_REQUEST) 获取并跳转
		if (requestAttributes != null) {
			requestAttributes.setAttribute("redirectUrl", decodeState, RequestAttributes.SCOPE_REQUEST);
		}
		// 当然 decodeState 也可以传递从前端传到后端的用户信息, 注册到本地用户

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(defaultAuthority);

		//用户注册逻辑
		User user = new User();
		userMapper.insert(user);
		return user.getId();
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

	@Override
	public String[] generateUsernames(AuthUser authUser) {
		return new String[]{
			authUser.getUsername(),
			// providerId = authUser.getSource()
			authUser.getUsername() + "_" + authUser.getSource(),
			// providerUserId = authUser.getUuid()
			authUser.getUsername() + "_" + authUser.getSource() + "_" + authUser.getUuid()
		};
	}

	@Override
	public List<Boolean> existedByUsernames(String[] usernames) {
		List<String> names = userMapper.existedByUsernames(usernames);
		return Arrays.stream(usernames).map(names::contains).collect(Collectors.toList());
	}

	public String authentication(String encodeState, AuthUser authUser, final String providerId, boolean autoSignUp) {

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
					loginId = SaTempUtil.createToken(authUser.getUuid(), authUser.getToken().getExpireIn());
					StpUtil.login(loginId);
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
				if (loginId == null) {
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
