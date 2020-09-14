package com.simple4j.user.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simple4j.autoconfigure.util.INode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单表详情响应实体类
 *
 * @author Blade
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "菜单表详情响应实体类", description = "菜单表详情响应实体类")
public class MenuDetailResponse implements INode<MenuDetailResponse, Long> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(name = "id", value = "主键")
	@JsonProperty("id")
	private Long id;
	/**
	 * 父级菜单
	 */
	@ApiModelProperty(name = "parent_id", value = "父级菜单")
	@JsonProperty("parent_id")
	private Long parentId;
	/**
	 * 菜单编号
	 */
	@ApiModelProperty(name = "code", value = "菜单编号")
	@JsonProperty("code")
	private String code;
	/**
	 * 菜单名称
	 */
	@ApiModelProperty(name = "name", value = "菜单名称")
	@JsonProperty("name")
	private String name;
	/**
	 * 菜单别名
	 */
	@ApiModelProperty(name = "alias", value = "菜单别名")
	@JsonProperty("alias")
	private String alias;
	/**
	 * 请求地址
	 */
	@ApiModelProperty(name = "path", value = "请求地址")
	@JsonProperty("path")
	private String path;
	/**
	 * 菜单资源
	 */
	@ApiModelProperty(name = "source", value = "菜单资源")
	@JsonProperty("source")
	private String source;
	/**
	 * 排序
	 */
	@ApiModelProperty(name = "sort", value = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 菜单类型
	 */
	@ApiModelProperty(name = "category", value = "菜单类型")
	@JsonProperty("category")
	private Integer category;
	/**
	 * 操作按钮类型
	 */
	@ApiModelProperty(name = "action", value = "操作按钮类型")
	@JsonProperty("action")
	private Integer action;
	/**
	 * 是否打开新页面
	 */
	@ApiModelProperty(name = "is_open", value = "是否打开新页面")
	@JsonProperty("is_open")
	private Integer isOpen;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark", value = "备注")
	@JsonProperty("remark")
	private String remark;
	/**
	 * 是否已删除
	 */
	@ApiModelProperty(name = "is_deleted", value = "是否已删除")
	@JsonProperty("is_deleted")
	private Integer isDeleted;


	/**
	 * 子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<MenuDetailResponse> children;

	/**
	 * 上级菜单
	 */
	@ApiModelProperty(name = "parent_name", value = "上级菜单")
	@JsonProperty("parent_name")
	private String parentName;

	/**
	 * 菜单类型
	 */
	@ApiModelProperty(name = "category_name", value = "菜单类型")
	@JsonProperty("category_name")
	private String categoryName;

	/**
	 * 按钮功能
	 */
	@ApiModelProperty(name = "action_name", value = "按钮功能")
	@JsonProperty("action_name")
	private String actionName;

	/**
	 * 是否新窗口打开
	 */
	@ApiModelProperty(name = "is_open_name", value = "是否新窗口打开")
	@JsonProperty("is_open_name")
	private String isOpenName;

	@Override
	public void addChildren(com.simple4j.user.response.MenuDetailResponse children) {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.add(children);
	}

	@JsonIgnore
	@Override
	public Long getTid() {
		return getId();
	}

	@JsonIgnore
	@Override
	public Long getTParentId() {
		return getParentId();
	}

	@Override
	public int compareTo(com.simple4j.user.response.MenuDetailResponse o) {
		if (o.getSort().equals(this.getSort())) {
			return 0;
		} else if (o.getSort() > this.getSort()) {
			return -1;
		} else {
			return 1;
		}
	}
}
