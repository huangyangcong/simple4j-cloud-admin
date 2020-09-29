package com.simple4j.system.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 详情请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "详情请求实体类", description = "详情请求实体类")
public class NavbarDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 详情编号
	 */
	@ApiModelProperty(value = "详情编号", name = "id")
	private String id;
}
