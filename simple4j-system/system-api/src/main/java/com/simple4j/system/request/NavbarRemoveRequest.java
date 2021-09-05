package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 删除请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "删除请求实体类", description = "删除请求实体类")
public class NavbarRemoveRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 删除编号列表
	 */
	@Parameter(description = "删除编号列表", name = "ids")
	@JsonProperty("ids")
	private List<String> ids;
}
