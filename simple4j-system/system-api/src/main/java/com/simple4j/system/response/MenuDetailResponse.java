package com.simple4j.system.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simple4j.api.base.INode;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单表详情响应实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "菜单表详情响应实体类", description = "菜单表详情响应实体类")
public class MenuDetailResponse implements INode<MenuDetailResponse, String> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Parameter(name = "id", description = "主键")
	@JsonProperty("id")
	@Getter(onMethod = @__({@JsonIgnore}))
	private String id;
	/**
	 * 父级菜单
	 */
	@Parameter(name = "parent_id", description = "父级菜单")
	@JsonProperty("parent_id")
	@Getter(onMethod = @__({@JsonIgnore}))
	private String parentId;
	/**
	 * 菜单编号
	 */
	@Parameter(name = "code", description = "菜单编号")
	@JsonProperty("code")
	private String code;
	/**
	 * 菜单名称
	 */
	@Parameter(name = "name", description = "菜单名称")
	@JsonProperty("name")
	private String name;
	/**
	 * 菜单别名
	 */
	@Parameter(name = "alias", description = "菜单别名")
	@JsonProperty("alias")
	private String alias;
	/**
	 * 请求地址
	 */
	@Parameter(name = "path", description = "请求地址")
	@JsonProperty("path")
	private String path;
	/**
	 * 菜单资源
	 */
	@Parameter(name = "source", description = "菜单资源")
	@JsonProperty("source")
	private String source;
	/**
	 * 排序
	 */
	@Parameter(name = "sort", description = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 菜单类型
	 */
	@Parameter(name = "category", description = "菜单类型")
	@JsonProperty("category")
	private Integer category;
	/**
	 * 操作按钮类型
	 */
	@Parameter(name = "action", description = "操作按钮类型")
	@JsonProperty("action")
	private Integer action;
	/**
	 * 是否打开新页面
	 */
	@Parameter(name = "is_open", description = "是否打开新页面")
	@JsonProperty("is_open")
	private Integer isOpen;
	/**
	 * 备注
	 */
	@Parameter(name = "remark", description = "备注")
	@JsonProperty("remark")
	private String remark;
	/**
	 * 是否已删除
	 */
	@Parameter(name = "is_deleted", description = "是否已删除")
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
	@Parameter(name = "parent_name", description = "上级菜单")
	@JsonProperty("parent_name")
	private String parentName;

	/**
	 * 菜单类型
	 */
	@Parameter(name = "category_name", description = "菜单类型")
	@JsonProperty("category_name")
	private String categoryName;

	/**
	 * 按钮功能
	 */
	@Parameter(name = "action_name", description = "按钮功能")
	@JsonProperty("action_name")
	private String actionName;

	/**
	 * 是否新窗口打开
	 */
	@Parameter(name = "is_open_name", description = "是否新窗口打开")
	@JsonProperty("is_open_name")
	private String isOpenName;
	/**
	 * 是否隐藏
	 */
	@Parameter(name = "hidden", description = "是否隐藏")
	@JsonProperty("hidden")
	private Integer hidden;

	@Override
	public void addChildren(MenuDetailResponse children) {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.add(children);
	}

	@Override
	public int compareTo(MenuDetailResponse o) {
		if (o.getSort().equals(this.getSort())) {
			return 0;
		} else if (o.getSort() > this.getSort()) {
			return -1;
		} else {
			return 1;
		}
	}
}
