package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 客户端表删除请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "客户端表删除请求实体类", description = "客户端表删除请求实体类")
public class ClientRemoveRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户端表删除编号列表
	 */
	@Parameter(description = "客户端表删除编号列表", name = "ids")
	@JsonProperty("ids")
	private List<String> ids;
}
