package com.simple4j.system.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 岗位表详情响应实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "岗位表详情响应实体类", description = "岗位表详情响应实体类")
public class PostDetailResponse implements Serializable {

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
	 * 岗位类型
	 */
	@Parameter(name = "category", description = "岗位类型")
	@JsonProperty("category")
	private Integer category;
	/**
	 * 岗位编号
	 */
	@Parameter(name = "post_code", description = "岗位编号")
	@JsonProperty("post_code")
	private String postCode;
	/**
	 * 岗位名称
	 */
	@Parameter(name = "post_name", description = "岗位名称")
	@JsonProperty("post_name")
	private String postName;
	/**
	 * 岗位排序
	 */
	@Parameter(name = "sort", description = "岗位排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 岗位描述
	 */
	@Parameter(name = "remark", description = "岗位描述")
	@JsonProperty("remark")
	private String remark;
	/**
	 * 创建人
	 */
	@Parameter(name = "create_user", description = "创建人")
	@JsonProperty("create_user")
	private String createUser;
	/**
	 * 创建部门
	 */
	@Parameter(name = "create_dept", description = "创建部门")
	@JsonProperty("create_dept")
	private Long createDept;
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
	/**
	 * 是否已删除
	 */
	@Parameter(name = "is_deleted", description = "是否已删除")
	@JsonProperty("is_deleted")
	private Integer isDeleted;

	/**
	 * 岗位分类名
	 */
	@Parameter(name = "category_name", description = "岗位分类名")
	@JsonProperty("category_name")
	private String categoryName;
}
