package com.simple4j.system.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典表详情请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "字典表详情请求实体类", description = "字典表详情请求实体类")
public class DictDetailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 字典表详情编号
	 */
	@ApiModelProperty(value = "字典表详情编号", name = "id")
	private Long id;
}
