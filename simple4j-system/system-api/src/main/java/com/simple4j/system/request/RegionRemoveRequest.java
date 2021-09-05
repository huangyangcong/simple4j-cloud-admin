package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 行政区划表删除请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "行政区划表删除请求实体类", description = "行政区划表删除请求实体类")
public class RegionRemoveRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 行政区划表删除编号列表
	 */
	@Parameter(description = "行政区划表删除编号列表", name = "id")
	@JsonProperty("id")
	private String id;
}
