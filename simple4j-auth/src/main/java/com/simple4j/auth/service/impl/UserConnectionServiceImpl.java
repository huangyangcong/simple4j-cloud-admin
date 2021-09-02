package com.simple4j.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simple4j.auth.entity.User;
import com.simple4j.auth.entity.UserConnection;
import com.simple4j.auth.mapper.UserConnectionMapper;
import com.simple4j.auth.service.IAuthTokenService;
import com.simple4j.auth.service.IUserConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务类
 *
 * @author hyc
 */
@Service
@RequiredArgsConstructor
public class UserConnectionServiceImpl implements IUserConnectionService {
	private final UserConnectionMapper userConnectionMapper;

	@Override
	public List<UserConnection> queryConnectionByProviderIdAndProviderUserId(String providerId, String providerUserId) {
		return userConnectionMapper.selectList(new LambdaQueryWrapper<UserConnection>()
			.eq(UserConnection::getProviderid, providerId)
			.eq(UserConnection::getUserid, providerUserId)
		);
	}
}
