package com.simple4j.common.api.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * 公共响应参数
 *
 * @author hyc
 */
public class ApiResponse<T> implements Serializable {

	private static final long serialVersionUID = -3723220666104464201L;
	/**
	 * 响应码
	 */
	private int code;

	/**
	 * 响应消息
	 */
	private String msg;
	/**
	 *
	 */
	private String subCode;
	/**
	 *
	 */
	private String subMsg;
	/**
	 * 响应体
	 */
	private String body;

	private Map<String, String> params;

	private T response;

	public static <T> ApiResponse<T> success(T result) {
		ApiResponse<T> response = new ApiResponse<T>();
		response.setCode(200);
		response.setSubCode("200");
		response.setResponse(result);
		return response;
	}

	public static <T> ApiResponse<T> error(T result) {
		return error(500, "", result);
	}

	public static <T> ApiResponse<T> error(int code, String msg) {
		return error(code, msg, null);
	}

	public static <T> ApiResponse<T> error(int code, String msg, T result) {
		ApiResponse<T> response = new ApiResponse<T>();
		response.setCode(code);
		response.setMsg(msg);
		response.setResponse(result);
		return response;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubMsg() {
		return subMsg;
	}

	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
