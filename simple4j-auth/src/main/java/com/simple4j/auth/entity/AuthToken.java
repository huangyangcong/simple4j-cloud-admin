package com.simple4j.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author hyc
 * @email
 * @date 2021-09-01 18:22:57
 */
@Data
@TableName("auth_token")
public class AuthToken extends com.simple4j.auth.models.AuthToken {
	private static final long serialVersionUID = 1L;

	/**
	 * tokenId
	 */
	@TableId
	private Long id;
}
