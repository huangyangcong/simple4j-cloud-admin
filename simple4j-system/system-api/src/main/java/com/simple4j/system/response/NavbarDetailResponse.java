package com.simple4j.system.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simple4j.system.request.NavbarAddOrUpdateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.Date;

/**
 * 详情响应实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@Schema(name = "详情响应实体类", description = "详情响应实体类")
public class NavbarDetailResponse extends NavbarAddOrUpdateRequest {

	private static final long serialVersionUID = 1L;

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
