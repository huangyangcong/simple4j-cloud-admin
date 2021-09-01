package com.simple4j.auth.exceptions;

import com.simple4j.api.base.BusinessException;

public class UserNotFoundException extends BusinessException {
	public UserNotFoundException() {
		super("100", "用户名或密码错误");
	}
}
