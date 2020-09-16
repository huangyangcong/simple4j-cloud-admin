package com.simple4j.user.serivce.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import com.simple4j.autoconfigure.jwt.properties.JwtProperties;
import com.simple4j.autoconfigure.jwt.service.AbstractUserDetailsService;
import com.simple4j.user.dto.JwtDto;
import com.simple4j.user.response.UserInfo;
import com.simple4j.user.service.IUserService;
import com.simple4j.web.bean.ApiMsg;
import com.simple4j.web.bean.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

	@Override
	public void accessDeniedExceptionHandler(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException {
		//当用户在没有授权的情况下访问受保护的REST资源时，将调用此方法发送403 Forbidden响应
		response.setContentType(ContentType.JSON.toString(StandardCharsets.UTF_8));
		JSONUtil.toJsonStr(ApiResponse.to(ApiMsg.FORBIDDEN_ERROR), response.getWriter());
	}

	@Override
	public void authenticationExceptionHandler(javax.servlet.http.HttpServletRequest request,
											   javax.servlet.http.HttpServletResponse response,
											   AuthenticationException authenticationException) throws IOException {
		response.setContentType(ContentType.JSON.toString(StandardCharsets.UTF_8));
		// 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401 响应
		JSONUtil.toJsonStr(ApiResponse.to(ApiMsg.UNAUTHORIZED_ERROR), response.getWriter());
	}
}
