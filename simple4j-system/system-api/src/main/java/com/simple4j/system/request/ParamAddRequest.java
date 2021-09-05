package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 参数表新增请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "参数表新增请求实体类", description = "参数表新增请求实体类")
public class ParamAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数名
	 */
	@Parameter(name = "param_name", description = "参数名")
	@JsonProperty("param_name")
	private String paramName;
	/**
	 * 参数键
	 */
	@Parameter(name = "param_key", description = "参数键")
	@JsonProperty("param_key")
	private String paramKey;
	/**
	 * 参数值
	 */
	@Parameter(name = "param_value", description = "参数值")
	@JsonProperty("param_value")
	private String paramValue;
	/**
	 * 备注
	 */
	@Parameter(name = "remark", description = "备注")
	@JsonProperty("remark")
	private String remark;
	/**
	 * 创建人
	 */
	@Parameter(name = "create_user", description = "创建人")
	@JsonProperty("create_user")
	private String createUser;
	/**
	 * 创建时间
	 */
	@Parameter(name = "create_time", description = "创建时间")
	@JsonProperty("create_time")
	private Date createTime;
	/**
	 * 修改人
	 */
	@Parameter(name = "update_user", description = "修改人")
	@JsonProperty("update_user")
	private String updateUser;
	/**
	 * 修改时间
	 */
	@Parameter(name = "update_time", description = "修改时间")
	@JsonProperty("update_time")
	private Date updateTime;
	/**
	 * 状态
	 */
	@Parameter(name = "status", description = "状态")
	@JsonProperty("status")
	private Integer status;
}
