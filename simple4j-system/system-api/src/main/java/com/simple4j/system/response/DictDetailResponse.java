package com.simple4j.system.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simple4j.api.base.INode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

/**
 * 字典表详情响应实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "字典表详情响应实体类", description = "字典表详情响应实体类")
public class DictDetailResponse implements INode<DictDetailResponse, String> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(name = "id", value = "主键")
	@JsonProperty("id")
	@Getter(onMethod=@__({@JsonIgnore}))
	private String id;
	/**
	 * 父主键
	 */
	@ApiModelProperty(name = "parent_id", value = "父主键")
	@JsonProperty("parent_id")
	@Getter(onMethod=@__({@JsonIgnore}))
	private String parentId;
	/**
	 * 字典码
	 */
	@ApiModelProperty(name = "code", value = "字典码")
	@JsonProperty("code")
	private String code;
	/**
	 * 字典值
	 */
	@ApiModelProperty(name = "dict_key", value = "字典值")
	@JsonProperty("dict_key")
	private Integer dictKey;
	/**
	 * 字典名称
	 */
	@ApiModelProperty(name = "dict_value", value = "字典名称")
	@JsonProperty("dict_value")
	private String dictValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(name = "sort", value = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 字典备注
	 */
	@ApiModelProperty(name = "remark", value = "字典备注")
	@JsonProperty("remark")
	private String remark;
	/**
	 * 是否已删除
	 */
	@ApiModelProperty(name = "is_deleted", value = "是否已删除")
	@JsonProperty("is_deleted")
	private Integer isDeleted;

	/**
	 * 上级字典
	 */
	@ApiModelProperty(name = "parent_name", value = "上级字典")
	@JsonProperty("parent_name")
	private String parentName;

	/**
	 * 子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<DictDetailResponse> children;

	@Override
	public void addChildren(com.simple4j.system.response.DictDetailResponse children) {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.add(children);
	}

	@Override
	public int compareTo(com.simple4j.system.response.DictDetailResponse o) {
		if (o.getSort().equals(this.getSort())) {
			return 0;
		} else if (o.getSort() > this.getSort()) {
			return -1;
		} else {
			return 1;
		}
	}
}
