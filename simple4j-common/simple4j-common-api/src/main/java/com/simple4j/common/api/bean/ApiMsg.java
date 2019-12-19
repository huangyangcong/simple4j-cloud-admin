package com.simple4j.common.api.bean;

/**
 * @author hyc
 */
public enum ApiMsg {

	/**
	 * 错误信息
	 */
	MSG_1000("", ""),
	;
	private String code;
	private String msg;

	ApiMsg(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
