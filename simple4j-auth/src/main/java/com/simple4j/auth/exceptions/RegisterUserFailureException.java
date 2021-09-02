package com.simple4j.auth.exceptions;

import com.simple4j.api.base.BusinessException;
import com.simple4j.auth.enums.ErrorCodeEnum;

public class RegisterUserFailureException extends BusinessException {

	public RegisterUserFailureException(ErrorCodeEnum errorCodeEnum, Throwable t, String userId) {
		super(errorCodeEnum.getCode(), , t, null, userId);
	}

	public RegisterUserFailureException(ErrorCodeEnum errorCodeEnum, String userId) {
		super(errorCodeEnum, null, userId);
	}
}
