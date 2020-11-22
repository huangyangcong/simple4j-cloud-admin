package com.simple4j.system.request;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门表删除请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "部门表删除请求实体类", description = "部门表删除请求实体类")
public class DeptRemoveRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 部门表删除编号列表
	 */
	@ApiModelProperty(value = "部门表删除编号列表", name = "ids")
	@JsonProperty("ids")
	private Set<String> ids;
}
