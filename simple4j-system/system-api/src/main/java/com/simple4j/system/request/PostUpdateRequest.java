package com.simple4j.system.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 岗位表修改请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "岗位表修改请求实体类", description = "岗位表修改请求实体类")
public class PostUpdateRequest extends com.simple4j.system.request.PostAddRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 岗位表ID
	 */
	@ApiModelProperty(value = "岗位表ID")
	private String id;
}
