package com.simple4j.user.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * GrantVO
 *
 * @author Chill
 */
@Data
public class MenuGrantRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * roleIds集合
	 */
	@ApiModelProperty(value = "roleIds集合", name = "role_ids")
	@JsonProperty("role_ids")
	private List<Long> roleIds;

	/**
	 * menuIds集合
	 */
	@ApiModelProperty(value = "menuIds集合", name = "menu_ids")
	@JsonProperty("menu_ids")
	private List<Long> menuIds;

}
