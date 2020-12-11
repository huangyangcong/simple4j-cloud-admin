package com.simple4j.auth.filter;

import cn.hutool.core.util.StrUtil;
import com.simple4j.api.base.BusinessException;
import com.simple4j.auth.constant.AuthConstant;
import com.simple4j.auth.response.CaptchaResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UsernamePasswordAuthenticationExtendFilter extends UsernamePasswordAuthenticationFilter {

	public static final String SPRING_SECURITY_FORM_CAPTCHA_KEY = "captcha_key";

	public static final String SPRING_SECURITY_FORM_CAPTCHA_CODE = "captcha_code";

	private String captchaKey = SPRING_SECURITY_FORM_CAPTCHA_KEY;

	private String captchaCode = SPRING_SECURITY_FORM_CAPTCHA_CODE;

	public UsernamePasswordAuthenticationExtendFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		//图形验证码
		HttpSession session = request.getSession();
		CaptchaResponse captchaResponse = (CaptchaResponse)session.getAttribute(AuthConstant.CAPTCHA_CODE);
		if (captchaResponse != null) {
			String ck = request.getParameter(captchaKey);
			String cc = request.getParameter(captchaCode);
			// 判断验证码
			if (!ck.equals(captchaResponse.getKey()) || !StrUtil.equalsIgnoreCase(captchaResponse.getImage(), cc)) {
				throw new BusinessException("验证码错误");
			}
			//删除验证码
			session.removeAttribute(AuthConstant.CAPTCHA_CODE);
		}
		return super.attemptAuthentication(request, response);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute(AuthConstant.LOGIN_ERROR_COUNT, 0);
		super.successfulAuthentication(request, response, chain, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute(AuthConstant.LOGIN_ERROR_COUNT) == null) {
			session.setAttribute(AuthConstant.LOGIN_ERROR_COUNT, 0);
		}
		int count = (int)session.getAttribute(AuthConstant.LOGIN_ERROR_COUNT);
		session.setAttribute(AuthConstant.LOGIN_ERROR_COUNT, count + 1);
		super.unsuccessfulAuthentication(request, response, failed);
	}
}
