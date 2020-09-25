package com.simple4j.user.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.simple4j.user.request.NavbarAddOrUpdateRequest;

/**
 * 详情响应实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "详情响应实体类", description = "详情响应实体类")
public class NavbarDetailResponse extends NavbarAddOrUpdateRequest {

	private static final long serialVersionUID = 1L;


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
