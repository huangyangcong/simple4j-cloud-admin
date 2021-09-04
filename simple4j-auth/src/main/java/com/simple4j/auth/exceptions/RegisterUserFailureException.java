package com.simple4j.auth.exceptions;

import com.simple4j.api.base.BusinessException;
import com.simple4j.auth.enums.ErrorCodeEnum;

public class RegisterUserFailureException extends BusinessException {
	public RegisterUserFailureException(ErrorCodeEnum errorCode, Object data) {
		super((errorCode.getCode()), errorCode.getMsg(), data);
	}
	public RegisterUserFailureException(ErrorCodeEnum errorCode, Object data, Throwable e) {
		super((errorCode.getCode()), errorCode.getMsg(), data, e);
	}
}
