package com.simple4j.system.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 租户表详情响应实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "租户表详情响应实体类", description = "租户表详情响应实体类")
public class TenantDetailResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Parameter(name = "id", description = "主键")
	@JsonProperty("id")
	private String id;
	/**
	 * 租户编号
	 */
	@Parameter(name = "tenant_id", description = "租户编号")
	@JsonProperty("tenant_id")
	private String tenantId;
	/**
	 * 租户名称
	 */
	@Parameter(name = "tenant_name", description = "租户名称")
	@JsonProperty("tenant_name")
	private String tenantName;
	/**
	 * 联系人
	 */
	@Parameter(name = "linkman", description = "联系人")
	@JsonProperty("linkman")
	private String linkman;
	/**
	 * 联系电话
	 */
	@Parameter(name = "contact_number", description = "联系电话")
	@JsonProperty("contact_number")
	private String contactNumber;
	/**
	 * 联系地址
	 */
	@Parameter(name = "address", description = "联系地址")
	@JsonProperty("address")
	private String address;
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
	 * 是否已删除
	 */
	@Parameter(name = "is_deleted", description = "是否已删除")
	@JsonProperty("is_deleted")
	private Integer isDeleted;
}
