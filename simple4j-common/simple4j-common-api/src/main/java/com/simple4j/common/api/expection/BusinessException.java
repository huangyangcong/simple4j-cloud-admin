package com.simple4j.common.api.expection;

import com.simple4j.common.api.bean.ApiMsg;

/**
 * @author hyc
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 8616149076415621983L;

	private String code;

	private Object result;

	public BusinessException(ApiMsg apiMsg, Object... objs) {
		this(null, apiMsg, objs);
	}

	public BusinessException(Object result, ApiMsg apiMsg, Object... objs) {
		this(apiMsg.getCode(), String.format(apiMsg.getMsg(), objs), result);
	}

	private BusinessException(String code, String message, Object result) {
		super(message);
		this.code = code;
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public Object getResult() {
		return result;
	}
}
