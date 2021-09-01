package com.simple4j.auth.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.auth.entity.User;
import com.simple4j.auth.mapper.UserMapper;
import com.simple4j.auth.service.IRoleMenuService;
import com.simple4j.auth.service.IUserRoleService;
import com.simple4j.auth.service.IUserService;
import com.simple4j.autoconfigure.jwt.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author hyc
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UmsUserDetailsService {

	private final UserMapper userMapper;
	private final IUserRoleService userRoleService;
	private final IRoleMenuService roleMenuService;
	private final PasswordEncoder passwordEncoder;
	private final IUserService userService;

	@Override
	public UserDetails registerUser(
		AuthUser authUser, String username, String defaultAuthority, String decodeState)
		throws RegisterUserFailureException {
		// username=1352640897,
		// authUser={"uuid":"27630","username":"1352640897","nickname":"huangyangcong","avatar":"https://gitee.com/assets/no_portrait.png","blog":null,"company":null,"location":null,"email":null,"remark":"","gender":"UNKNOWN","source":"gitee","token":{"accessToken":"ef25288368536297b26bfc24e907d7ca","expireIn":86400,"refreshToken":"e4547381c5e33f410cc598a5076438a3b08b8439b20f0df1c856118503fb9b4e","uid":null,"openId":null,"accessCode":null,"unionId":null,"scope":"user_info","tokenType":"bearer","idToken":null,"macAlgorithm":null,"macKey":null,"code":null,"oauthToken":null,"oauthTokenSecret":null,"userId":null,"screenName":null,"oauthCallbackConfirmed":null},"rawUserInfo":{"gists_url":"https://gitee.com/api/v5/users/1352640897/gists{/gist_id}","repos_url":"https://gitee.com/api/v5/users/1352640897/repos","following_url":"https://gitee.com/api/v5/users/1352640897/following_url{/other_user}","bio":"","created_at":"2013-06-28T08:00:00+08:00","login":"1352640897","type":"User","blog":null,"subscriptions_url":"https://gitee.com/api/v5/users/1352640897/subscriptions","weibo":null,"updated_at":"2020-11-26T23:51:46+08:00","id":27630,"public_repos":13,"email":null,"organizations_url":"https://gitee.com/api/v5/users/1352640897/orgs","starred_url":"https://gitee.com/api/v5/users/1352640897/starred{/owner}{/repo}","followers_url":"https://gitee.com/api/v5/users/1352640897/followers","public_gists":0,"url":"https://gitee.com/api/v5/users/1352640897","received_events_url":"https://gitee.com/api/v5/users/1352640897/received_events","watched":15,"followers":0,"avatar_url":"https://gitee.com/assets/no_portrait.png","events_url":"https://gitee.com/api/v5/users/1352640897/events{/privacy}","html_url":"https://gitee.com/1352640897","following":0,"name":"huangyangcong","stared":4}}

		// 第三方授权登录不需要密码, 这里随便设置的, 生成环境按自己的逻辑
		String encodedPassword = passwordEncoder.encode(authUser.getUuid());

		// 这里的 decodeState 可以根据自己实现的 top.dcenter.ums.security.core.oauth.service.Auth2StateCoder
		// 接口的逻辑来传递必要的参数.
		// 比如: 第三方登录成功后的跳转地址
		final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		// 假设 decodeState 就是 redirectUrl, 我们直接把 redirectUrl 设置到 request 上
		// 后续经过成功处理器时直接从 requestAttributes.getAttribute("redirectUrl", RequestAttributes.SCOPE_REQUEST)
		// 获取并跳转
		requestAttributes.setAttribute("redirectUrl", decodeState, RequestAttributes.SCOPE_REQUEST);
		// 当然 decodeState 也可以传递从前端传到后端的用户信息, 注册到本地用户

		List<GrantedAuthority> grantedAuthorities =
			AuthorityUtils.commaSeparatedStringToAuthorityList(defaultAuthority);

		User user = new User();
		user.setAccount(username);
		user.setPassword(encodedPassword);
		user.setAvatar(authUser.getAvatar());
		user.setName(authUser.getNickname());
		userService.registerUser(user);

		log.info("Demo ======>: 用户名：{}, 注册成功", username);

		// @formatter:off
		UserDetails userDetails =
			org.springframework.security.core.userdetails.User.builder()
				.username(username)
				.password(encodedPassword)
				.disabled(false)
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.authorities(grantedAuthorities)
				.build();
		// @formatter:off
		return userDetails;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getAccount, username));
		if (ObjectUtil.isEmpty(user)) {
			throw new UsernameNotFoundException("");
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		Set<String> roleIds = userRoleService.getRoleIds(user.getId());
		for (String role : roleIds) {
			grantedAuthorities.add(SecurityUtils.createRoleAuthority(role));
		}
		Set<String> permissions = roleMenuService.getPermission(roleIds);
		for (String permission : permissions) {
			grantedAuthorities.add(SecurityUtils.createPermissionAuthority(permission));
		}

		if (StrUtil.isNotBlank(user.getTenantId())) {
			grantedAuthorities.add(SecurityUtils.createTenantAuthority(user.getTenantId()));
		}
		if (StrUtil.isNotBlank(user.getId())) {
			grantedAuthorities.add(SecurityUtils.createUserIdAuthority(user.getId()));
		}
		if (StrUtil.isNotBlank(user.getName())) {
			grantedAuthorities.add(SecurityUtils.createUserNameAuthority(user.getName()));
		}
		return org.springframework.security.core.userdetails.User.withUsername(user.getName())
			.password(user.getPassword())
			.authorities(grantedAuthorities)
			.build();
	}

	@Override
	public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		return this.loadUserByUsername(userId);
	}

	@Override
	public List<Boolean> existedByUsernames(String... usernames) {
		List<Boolean> result = new ArrayList<>();
		if (ArrayUtil.isEmpty(usernames)) {
			return result;
		}

		for (String username : usernames) {
			result.add(userMapper.existedByAccount(username));
		}
		return result;
	}
}
