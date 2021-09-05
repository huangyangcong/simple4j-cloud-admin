package com.simple4j.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 详情请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "详情请求实体类", description = "详情请求实体类")
public class NavbarMenuDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 详情编号
	 */
	@Parameter(description = "详情编号", name = "id")
	private String id;
}
