package com.simple4j.auth.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xkcoding.http.config.HttpConfig;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.Instant;

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



	public void copyProperties(com.simple4j.auth.models.AuthToken authToken) {
		BeanUtil.copyProperties(authToken, this);
	}
}
