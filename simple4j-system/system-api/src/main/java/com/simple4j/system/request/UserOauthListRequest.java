package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户第三方认证表列表请求实体类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Data
@Schema(name = "用户第三方认证表列表请求实体类", description = "用户第三方认证表列表请求实体类")
public class UserOauthListRequest implements Serializable {

	private static final long serialVersionUID = 1L;
}
