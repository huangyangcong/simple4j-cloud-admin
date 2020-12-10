package com.simple4j.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserSession implements Serializable {
	private String captchaKey;
	private String captchaCode;
	private Date captchaExpired;
	private Integer loginErrorCode;
}
