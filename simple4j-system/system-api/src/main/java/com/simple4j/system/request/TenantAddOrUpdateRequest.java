package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 租户表新增请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "租户表新增请求实体类", description = "租户表新增请求实体类")
public class TenantAddOrUpdateRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 租户名称
	 */
	@ApiModelProperty(name = "tenant_name", value = "租户名称")
	@JsonProperty("tenant_name")
	private String tenantName;
	/**
	 * 联系人
	 */
	@ApiModelProperty(name = "linkman", value = "联系人")
	@JsonProperty("linkman")
	private String linkman;
	/**
	 * 联系电话
	 */
	@ApiModelProperty(name = "contact_number", value = "联系电话")
	@JsonProperty("contact_number")
	private String contactNumber;
	/**
	 * 联系地址
	 */
	@ApiModelProperty(name = "address", value = "联系地址")
	@JsonProperty("address")
	private String address;
	/**
	 * 创建人
	 */
	@ApiModelProperty(name = "create_user", value = "创建人")
	@JsonProperty("create_user")
	private String createUser;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(name = "create_time", value = "创建时间")
	@JsonProperty("create_time")
	private Date createTime;
	/**
	 * 修改人
	 */
	@ApiModelProperty(name = "update_user", value = "修改人")
	@JsonProperty("update_user")
	private String updateUser;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(name = "update_time", value = "修改时间")
	@JsonProperty("update_time")
	private Date updateTime;
	/**
	 * 状态
	 */
	@ApiModelProperty(name = "status", value = "状态")
	@JsonProperty("status")
	private Integer status;
}
