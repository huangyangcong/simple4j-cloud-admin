package com.simple4j.user.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 租户表详情响应实体类
 *
 * @author Blade
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "租户表详情响应实体类", description = "租户表详情响应实体类")
public class TenantDetailResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(name = "id", value = "主键")
	@JsonProperty("id")
	private Long id;
	/**
	 * 租户编号
	 */
	@ApiModelProperty(name = "tenant_id", value = "租户编号")
	@JsonProperty("tenant_id")
	private String tenantId;
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
	private Long createUser;
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
	private Long updateUser;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(name = "update_time", value = "修改时间")
	@JsonProperty("update_time")
	private Date updateTime;

	/**
	 * 是否已删除
	 */
	@ApiModelProperty(name = "is_deleted", value = "是否已删除")
	@JsonProperty("is_deleted")
	private Integer isDeleted;


}
