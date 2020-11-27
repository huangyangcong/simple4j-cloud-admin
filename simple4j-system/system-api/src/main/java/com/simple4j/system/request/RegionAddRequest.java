package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 行政区划表新增请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@ApiModel(value = "行政区划表新增请求实体类", description = "行政区划表新增请求实体类")
public class RegionAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

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
}
