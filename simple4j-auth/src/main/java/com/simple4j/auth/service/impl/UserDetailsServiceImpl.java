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
