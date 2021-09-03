package com.simple4j.auth.service.impl;

import com.simple4j.auth.entity.AuthToken;
import com.simple4j.auth.mapper.AuthTokenMapper;
import com.simple4j.auth.service.IAuthTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务类
 *
 * @author hyc
 */
@Service
@AllArgsConstructor
public class AuthTokenServiceImpl implements IAuthTokenService {
	private final AuthTokenMapper authTokenMapper;

	@Override
	public void saveAuthToken(AuthToken authToken) {
		authTokenMapper.insert(authToken);
	}

	@Override
	public void updateAuthToken(AuthToken authToken) {
		authTokenMapper.updateById(authToken);
	}
}
