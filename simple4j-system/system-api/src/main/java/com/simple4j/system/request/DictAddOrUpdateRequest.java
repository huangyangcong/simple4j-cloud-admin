package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典表新增请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "字典表新增请求实体类", description = "字典表新增请求实体类")
public class DictAddOrUpdateRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 字典表ID
	 */
	@ApiModelProperty(value = "字典表ID")
	private String id;

	/**
	 * 父主键
	 */
	@ApiModelProperty(name = "parent_id", value = "父主键")
	@JsonProperty("parent_id")
	private Long parentId;
	/**
	 * 字典码
	 */
	@ApiModelProperty(name = "code", value = "字典码")
	@JsonProperty("code")
	private String code;
	/**
	 * 字典值
	 */
	@ApiModelProperty(name = "dict_key", value = "字典值")
	@JsonProperty("dict_key")
	private Integer dictKey;
	/**
	 * 字典名称
	 */
	@ApiModelProperty(name = "dict_value", value = "字典名称")
	@JsonProperty("dict_value")
	private String dictValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(name = "sort", value = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 字典备注
	 */
	@ApiModelProperty(name = "remark", value = "字典备注")
	@JsonProperty("remark")
	private String remark;


}
