package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典表新增请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "字典表新增请求实体类", description = "字典表新增请求实体类")
public class DictAddOrUpdateRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 字典表ID
	 */
	@Parameter(description = "字典表ID")
	private String id;

	/**
	 * 父主键
	 */
	@Parameter(name = "parent_id", description = "父主键")
	@JsonProperty("parent_id")
	private Long parentId;
	/**
	 * 字典码
	 */
	@Parameter(name = "code", description = "字典码")
	@JsonProperty("code")
	private String code;
	/**
	 * 字典值
	 */
	@Parameter(name = "dict_key", description = "字典值")
	@JsonProperty("dict_key")
	private Integer dictKey;
	/**
	 * 字典名称
	 */
	@Parameter(name = "dict_value", description = "字典名称")
	@JsonProperty("dict_value")
	private String dictValue;
	/**
	 * 排序
	 */
	@Parameter(name = "sort", description = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 字典备注
	 */
	@Parameter(name = "remark", description = "字典备注")
	@JsonProperty("remark")
	private String remark;
}
