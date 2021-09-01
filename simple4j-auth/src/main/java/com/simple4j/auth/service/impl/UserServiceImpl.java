package com.simple4j.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.simple4j.auth.entity.User;
import com.simple4j.auth.mapper.UserMapper;
import com.simple4j.auth.request.UserLoginRequest;
import com.simple4j.auth.response.UserLoginResponse;
import com.simple4j.auth.service.ICaptchaService;
import com.simple4j.auth.service.IUserService;
import com.simple4j.autoconfigure.jwt.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author hyc
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ICaptchaService captchaService;

	@Override
	public void registerUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public String login(UserLoginRequest userLoginRequest) {
		String username = userLoginRequest.getUsername();
		User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getAccount, username));
		if (ObjectUtil.isEmpty(user)) {
			throw new UsernameNotFoundException("");
		}
		String password = user.getPassword();
		if (password.equals(userLoginRequest.getPassword())) {
			throw new UsernameNotFoundException("");
		}
		String captchaKey = userLoginRequest.getCaptchaKey();
		// 校验验证码
		captchaService.verify(username, captchaKey, userLoginRequest.getCaptchaCode());
		return null;
	}
}
