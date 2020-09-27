package com.simple4j.system.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simple4j.api.base.INode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hyc
 * @version 1.0.0
 */
@Data
public class RegionDetailResponse implements INode<RegionDetailResponse, String> {

	private static final long serialVersionUID = 1L;


	/**
	 * 区划编号
	 */
	@ApiModelProperty(name = "code", value = "区划编号")
	@JsonProperty("code")
	private String code;
	/**
	 * 父区划编号
	 */
	@ApiModelProperty(name = "parent_code", value = "父区划编号")
	@JsonProperty("parent_code")
	private String parentCode;
	/**
	 * 祖区划编号
	 */
	@ApiModelProperty(name = "ancestors", value = "祖区划编号")
	@JsonProperty("ancestors")
	private String ancestors;
	/**
	 * 区划名称
	 */
	@ApiModelProperty(name = "name", value = "区划名称")
	@JsonProperty("name")
	private String name;
	/**
	 * 省级区划编号
	 */
	@ApiModelProperty(name = "province_code", value = "省级区划编号")
	@JsonProperty("province_code")
	private String provinceCode;
	/**
	 * 省级名称
	 */
	@ApiModelProperty(name = "province_name", value = "省级名称")
	@JsonProperty("province_name")
	private String provinceName;
	/**
	 * 市级区划编号
	 */
	@ApiModelProperty(name = "city_code", value = "市级区划编号")
	@JsonProperty("city_code")
	private String cityCode;
	/**
	 * 市级名称
	 */
	@ApiModelProperty(name = "city_name", value = "市级名称")
	@JsonProperty("city_name")
	private String cityName;
	/**
	 * 区级区划编号
	 */
	@ApiModelProperty(name = "district_code", value = "区级区划编号")
	@JsonProperty("district_code")
	private String districtCode;
	/**
	 * 区级名称
	 */
	@ApiModelProperty(name = "district_name", value = "区级名称")
	@JsonProperty("district_name")
	private String districtName;
	/**
	 * 镇级区划编号
	 */
	@ApiModelProperty(name = "town_code", value = "镇级区划编号")
	@JsonProperty("town_code")
	private String townCode;
	/**
	 * 镇级名称
	 */
	@ApiModelProperty(name = "town_name", value = "镇级名称")
	@JsonProperty("town_name")
	private String townName;
	/**
	 * 村级区划编号
	 */
	@ApiModelProperty(name = "village_code", value = "村级区划编号")
	@JsonProperty("village_code")
	private String villageCode;
	/**
	 * 村级名称
	 */
	@ApiModelProperty(name = "village_name", value = "村级名称")
	@JsonProperty("village_name")
	private String villageName;
	/**
	 * 层级
	 */
	@ApiModelProperty(name = "level", value = "层级")
	@JsonProperty("level")
	private Integer level;
	/**
	 * 排序
	 */
	@ApiModelProperty(name = "sort", value = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark", value = "备注")
	@JsonProperty("remark")
	private String remark;

	/**
	 * 父节点名称
	 */
	@ApiModelProperty(name = "parent_name", value = "父节点名称")
	@JsonProperty("parent_name")
	private String parentName;


	/**
	 * 子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<RegionDetailResponse> children;

	@Override
	public void addChildren(RegionDetailResponse children) {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.add(children);
	}

	@Override
	public String getId() {
		return getCode();
	}

	@Override
	public String getParentId() {
		return getParentCode();
	}

	@Override
	public int compareTo(RegionDetailResponse o) {
		if (o.getSort().equals(this.getSort())) {
			return 0;
		} else if (o.getSort() > this.getSort()) {
			return -1;
		} else {
			return 1;
		}
	}
}
