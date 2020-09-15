package com.simple4j.user.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色表分页请求实体类
 *
 * @author Blade
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "角色表分页请求实体类", description = "角色表分页请求实体类")
public class RolePageRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	@ApiModelProperty(name = "page_no", value = "页码")
	@JsonProperty("page_no")
	private int pageNo;

	/**
	 * 分页数
	 */
	@ApiModelProperty(name = "pageSize", value = "分页数")
	@JsonProperty("page_size")
	private int pageSize;
}
