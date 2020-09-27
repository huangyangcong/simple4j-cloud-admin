package com.simple4j.system.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 详情响应实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "详情响应实体类", description = "详情响应实体类")
public class NavbarMenuDetailResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 按钮编号
	 */
	@ApiModelProperty(name = "menu_id", value = "按钮编号")
	@JsonProperty("menu_id")
	private Long menuId;
	/**
	 * 顶部按钮编号
	 */
	@ApiModelProperty(name = "top_menu_id", value = "顶部按钮编号")
	@JsonProperty("top_menu_id")
	private Long NavbarId;
	/**
	 * 主键
	 */
	@ApiModelProperty(name = "id", value = "主键")
	@JsonProperty("id")
	private Long id;
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
