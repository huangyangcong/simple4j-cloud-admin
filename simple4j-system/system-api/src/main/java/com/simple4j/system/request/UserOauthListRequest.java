package com.simple4j.system.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 用户第三方认证表列表请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "用户第三方认证表列表请求实体类", description = "用户第三方认证表列表请求实体类")
public class UserOauthListRequest implements Serializable {

	private static final long serialVersionUID = 1L;

}
