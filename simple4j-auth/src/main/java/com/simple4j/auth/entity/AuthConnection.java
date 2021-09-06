package com.simple4j.auth.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author hyc
 * @date 2021-09-01 18:22:57
 */
@Data
@SuperBuilder
@TableName("user_connection")
@NoArgsConstructor
public class AuthConnection extends com.simple4j.auth.models.AuthConnection {
	private static final long serialVersionUID = 1L;

	/**
	 * 本地用户id
	 */
	@TableId
	private String userId;

	public void copyProperties(com.simple4j.auth.models.AuthConnection authConnection) {
		BeanUtil.copyProperties(authConnection, this);
	}
}
