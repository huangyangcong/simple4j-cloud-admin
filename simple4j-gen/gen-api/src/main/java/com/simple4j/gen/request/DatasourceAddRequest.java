package com.simple4j.gen.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据源配置表新增请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@Schema(name = "数据源配置表新增请求实体类", description = "数据源配置表新增请求实体类")
public class DatasourceAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	@Parameter(name = "name", description = "名称")
	@JsonProperty("name")
	private String name;
	/**
	 * 驱动类
	 */
	@Parameter(name = "driver_class", description = "驱动类")
	@JsonProperty("driver_class")
	private String driverClass;
	/**
	 * 连接地址
	 */
	@Parameter(name = "url", description = "连接地址")
	@JsonProperty("url")
	private String url;
	/**
	 * 用户名
	 */
	@Parameter(name = "username", description = "用户名")
	@JsonProperty("username")
	private String username;
	/**
	 * 密码
	 */
	@Parameter(name = "password", description = "密码")
	@JsonProperty("password")
	private String password;
	/**
	 * 备注
	 */
	@Parameter(name = "remark", description = "备注")
	@JsonProperty("remark")
	private String remark;
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
	 * 状态
	 */
	@Parameter(name = "status", description = "状态")
	@JsonProperty("status")
	private Integer status;
}
