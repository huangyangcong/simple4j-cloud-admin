package com.simple4j.user.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行政区划表删除请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "行政区划表删除请求实体类", description = "行政区划表删除请求实体类")
public class RegionRemoveRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 行政区划表删除编号列表
	 */
	@ApiModelProperty(value = "行政区划表删除编号列表", name = "id")
	@JsonProperty("id")
	private String id;
}
