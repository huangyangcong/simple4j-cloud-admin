package com.simple4j.system.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 岗位表详情响应实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "岗位表详情响应实体类", description = "岗位表详情响应实体类")
public class PostDetailResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(name = "id", value = "主键")
	@JsonProperty("id")
	private String id;

	/**
	 * 租户编号
	 */
	@ApiModelProperty(name = "tenant_id", value = "租户编号")
	@JsonProperty("tenant_id")
	private String tenantId;

	/**
	 * 岗位类型
	 */
	@ApiModelProperty(name = "category", value = "岗位类型")
	@JsonProperty("category")
	private Integer category;
	/**
	 * 岗位编号
	 */
	@ApiModelProperty(name = "post_code", value = "岗位编号")
	@JsonProperty("post_code")
	private String postCode;
	/**
	 * 岗位名称
	 */
	@ApiModelProperty(name = "post_name", value = "岗位名称")
	@JsonProperty("post_name")
	private String postName;
	/**
	 * 岗位排序
	 */
	@ApiModelProperty(name = "sort", value = "岗位排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 岗位描述
	 */
	@ApiModelProperty(name = "remark", value = "岗位描述")
	@JsonProperty("remark")
	private String remark;
	/**
	 * 创建人
	 */
	@ApiModelProperty(name = "create_user", value = "创建人")
	@JsonProperty("create_user")
	private Long createUser;
	/**
	 * 创建部门
	 */
	@ApiModelProperty(name = "create_dept", value = "创建部门")
	@JsonProperty("create_dept")
	private Long createDept;
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
	 * 状态
	 */
	@ApiModelProperty(name = "status", value = "状态")
	@JsonProperty("status")
	private Integer status;
	/**
	 * 是否已删除
	 */
	@ApiModelProperty(name = "is_deleted", value = "是否已删除")
	@JsonProperty("is_deleted")
	private Integer isDeleted;

	/**
	 * 岗位分类名
	 */
	@ApiModelProperty(name = "category_name", value = "岗位分类名")
	@JsonProperty("category_name")
	private String categoryName;


}
