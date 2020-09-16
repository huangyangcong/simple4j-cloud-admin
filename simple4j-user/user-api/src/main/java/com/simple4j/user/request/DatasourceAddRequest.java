package com.simple4j.user.request;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据源配置表新增请求实体类
 *
 * @author Blade
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "数据源配置表添新增请求实体类", description = "数据源配置表添新增请求实体类")
public class DatasourceAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

		/**
	 * 名称
	 */
	@ApiModelProperty(name = "name", value = "名称")
	@JsonProperty("name")
	private String name;
	/**
	 * 驱动类
	 */
	@ApiModelProperty(name = "driver_class", value = "驱动类")
	@JsonProperty("driver_class")
	private String driverClass;
	/**
	 * 连接地址
	 */
	@ApiModelProperty(name = "url", value = "连接地址")
	@JsonProperty("url")
	private String url;
	/**
	 * 用户名
	 */
	@ApiModelProperty(name = "username", value = "用户名")
	@JsonProperty("username")
	private String username;
	/**
	 * 密码
	 */
	@ApiModelProperty(name = "password", value = "密码")
	@JsonProperty("password")
	private String password;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark", value = "备注")
	@JsonProperty("remark")
	private String remark;
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
	 * 状态
	 */
	@ApiModelProperty(name = "status", value = "状态")
	@JsonProperty("status")
	private Integer status;
	

}
