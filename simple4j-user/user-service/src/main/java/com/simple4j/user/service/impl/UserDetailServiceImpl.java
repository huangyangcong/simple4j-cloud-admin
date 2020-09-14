package com.simple4j.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.collection.CollUtil;
import com.newdex.web.jwt.security.JwtProperties;
import com.newdex.web.jwt.service.AbstractUserDetailsService;
import com.simple4j.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import com.simple4j.user.dto.JwtDto;
import com.simple4j.user.response.UserInfo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl extends AbstractUserDetailsService<JwtDto> {


	private final IUserService userService;
	private final RedisTemplate redisTemplate;
	private final JwtProperties jwtProperties;

	@Override
	public void save(JwtDto userDetails, String token) {
		redisTemplate.opsForValue().set(jwtProperties.getOnlineKey() + token, userDetails,
			jwtProperties.getTokenValidityInSeconds(), TimeUnit.MILLISECONDS);
		redisTemplate.opsForSet()
			.add(jwtProperties.getOnlineKey() + userDetails.getUsername(), token);
	}

	@Override
	public JwtDto get(String token) {
		return (JwtDto) redisTemplate.opsForValue().get(jwtProperties.getOnlineKey() + token);
	}

	@Override
	public boolean checkExpire(String token) {
		return redisTemplate.opsForValue().get(jwtProperties.getOnlineKey() + token) != null;
	}

	@Override
	public void removeOtherToken(String userName, String ignoreToken) {
		Set<Object> tokens = redisTemplate.opsForSet()
			.members(jwtProperties.getOnlineKey() + userName);
		if (CollUtil.isEmpty(tokens)) {
			return;
		}
		List<Object> delTokens = new ArrayList<>();
		List<String> delKeys = new ArrayList<>();
		for (Object token : tokens) {
			if (!token.equals(ignoreToken)) {
				delTokens.add(token);
				delKeys.add(jwtProperties.getOnlineKey() + token);
			}
		}
		if (CollUtil.isNotEmpty(delTokens)) {
			redisTemplate.opsForSet()
				.remove(jwtProperties.getOnlineKey() + userName, delTokens.toArray());
		}
		if (CollUtil.isNotEmpty(delKeys)) {
			redisTemplate.delete(delKeys);
		}
	}

	@Override
	public void logout(String username) {
		removeOtherToken(username, null);
	}

	@Override
	public long getExpire(String token) {
		return redisTemplate.getExpire(jwtProperties.getOnlineKey() + token, TimeUnit.MILLISECONDS);
	}

	@Override
	public void setExpire(String token, long renew, TimeUnit milliseconds) {
		redisTemplate.expire(jwtProperties.getOnlineKey() + token, renew, milliseconds);
	}


	@Override
	public UserDetails loadUserByUsername(
		String username) throws UsernameNotFoundException {
		UserInfo userIofo = userService.loadUserByUsername(username);
		if (userIofo == null) {
			throw new UsernameNotFoundException("");
		}
		return new JwtDto(userIofo);
	}
}
