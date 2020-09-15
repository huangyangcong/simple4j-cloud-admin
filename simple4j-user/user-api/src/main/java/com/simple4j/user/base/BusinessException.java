package com.simple4j.user.base;

/**
 * @author hyc
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -2052826482390897850L;

	private String code = "500";

	public String getCode() {
		return code;
	}

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	protected BusinessException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
