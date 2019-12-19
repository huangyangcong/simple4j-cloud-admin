package com.simple4j.common.api.bean;

import java.io.Serializable;

/**
 * 公共请求参数
 *
 * @author hyc
 */
public class ApiRequest<T> implements Serializable {
	private static final long serialVersionUID = -1794067169462136370L;
	/**
	 * 应用ID
	 */
	private String appId;
	/**
	 * 仅支持JSON
	 */
	private String format = ApiConstant.FORMAT_JSON;
	/**
	 * 请求使用的编码格式，如utf-8,gbk,gb2312等
	 */
	private String charset;
	/**
	 * 签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
	 */
	private String signType;
	/**
	 * 请求参数的签名串
	 */
	private String sign;
	/**
	 * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
	 */
	private String timestamp;
	/**
	 * 调用的接口版本，固定为：1.0
	 */
	private String version;
	private String appAuthToken;
	/**
	 * 请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递
	 */
	private String bizContent;

	private T bizRequest;


	public T getBizRequest() {
		return bizRequest;
	}

	public void setBizRequest(T bizRequest) {
		this.bizRequest = bizRequest;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAppAuthToken() {
		return appAuthToken;
	}

	public void setAppAuthToken(String appAuthToken) {
		this.appAuthToken = appAuthToken;
	}

	public String getBizContent() {
		return bizContent;
	}

	public void setBizContent(String bizContent) {
		this.bizContent = bizContent;
	}
}
