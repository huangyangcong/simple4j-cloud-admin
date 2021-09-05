package com.simple4j.system.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
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
@Schema(name = "详情响应实体类", description = "详情响应实体类")
public class NavbarMenuDetailResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 按钮编号
	 */
	@Parameter(name = "menu_id", description = "按钮编号")
	@JsonProperty("menu_id")
	private Long menuId;
	/**
	 * 顶部按钮编号
	 */
	@Parameter(name = "top_menu_id", description = "顶部按钮编号")
	@JsonProperty("top_menu_id")
	private Long NavbarId;
	/**
	 * 主键
	 */
	@Parameter(name = "id", description = "主键")
	@JsonProperty("id")
	private String id;
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
