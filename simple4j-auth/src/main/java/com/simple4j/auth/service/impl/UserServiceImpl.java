package com.simple4j.auth.service.impl;

import com.simple4j.auth.entity.User;
import com.simple4j.auth.mapper.UserMapper;
import com.simple4j.auth.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public void registerUser(User user) {
		userMapper.insert(user);
	}
}
