package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典表分页请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "字典表分页请求实体类", description = "字典表分页请求实体类")
public class DictPageRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	@Parameter(name = "page_no", description = "页码")
	@JsonProperty("page_no")
	private int pageNo;

	/**
	 * 分页数
	 */
	@Parameter(name = "pageSize", description = "分页数")
	@JsonProperty("page_size")
	private int pageSize;
}
