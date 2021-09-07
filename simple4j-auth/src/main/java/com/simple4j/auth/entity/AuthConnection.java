package com.simple4j.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author hyc
 * @date 2021-09-01 18:22:57
 */
@Data
@TableName("user_connection")
public class AuthConnection extends com.simple4j.auth.models.AuthConnection {
	private static final long serialVersionUID = 1L;

	/**
	 * 本地用户id
	 */
	@TableId
	private String userId;
}
