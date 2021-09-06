package com.simple4j.auth.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.auth.entity.AuthConnection;
import com.simple4j.auth.entity.AuthToken;
import com.simple4j.auth.entity.User;
import com.simple4j.auth.exceptions.UserNotFoundException;
import com.simple4j.auth.mapper.UserMapper;
import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.response.UserLoginResponse;
import com.simple4j.auth.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author hyc
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl extends AuthServiceAdapter<AuthToken, AuthConnection> implements IUserService {
	private final UserMapper userMapper;
	private final ICaptchaService captchaService;
	private final IUserConnectionService userConnectionService;
	private final IAuthTokenService authTokenService;

	@Override
	public String registerUser(AuthUser authUser, String username, String decodeState) {
		//用户注册逻辑
		User user = new User();
		user.setPassword(SaSecureUtil.md5BySalt("123456", username));
		user.setAccount(username);
		user.setRealName(username);
		user.setEmail(authUser.getEmail());
		user.setStatus(1);
		user.setAvatar(authUser.getAvatar());
		user.setName(authUser.getNickname());
		user.setSex(Integer.parseInt(authUser.getGender().getCode()));
		user.setCode(username);
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
	public AuthToken instanceToken() {
		return new AuthToken();
	}

	@Override
	public List<Boolean> existedByUsernames(String[] usernames) {
		List<String> names = userMapper.existedByUsernames(usernames);
		return Arrays.stream(usernames).map(names::contains).collect(Collectors.toList());
	}

	@Override
	public List<AuthConnection> queryConnectionByProviderIdAndProviderUserId(String providerId, String providerUserId) {
		return userConnectionService.queryConnectionByProviderIdAndProviderUserId(providerId, providerUserId);
	}

	@Override
	public void updateAuthConnection(AuthUser authUser, AuthConnection authConnection) {
		userConnectionService.updateUserConnection(authUser, authConnection);
	}

	@Override
	public void saveAuthConnection(String providerId, AuthUser authUser, String loginId, AuthToken authToken) {
		userConnectionService.saveAuthConnection(providerId, loginId, authUser, authToken);
	}

	@Override
	public void saveAuthToken(AuthToken authToken) {
		authTokenService.saveAuthToken(authToken);
	}


}
