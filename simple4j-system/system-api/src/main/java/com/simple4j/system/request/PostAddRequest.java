package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 岗位表新增请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "岗位表新增请求实体类", description = "岗位表新增请求实体类")
public class PostAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

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
	private String createUser;
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
