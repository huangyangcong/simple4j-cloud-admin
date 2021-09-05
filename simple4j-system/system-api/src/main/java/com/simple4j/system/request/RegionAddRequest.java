package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.io.Serializable;

/**
 * 行政区划表新增请求实体类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Data
@Schema(name = "行政区划表新增请求实体类", description = "行政区划表新增请求实体类")
public class RegionAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 父区划编号
	 */
	@Parameter(name = "parent_code", description = "父区划编号")
	@JsonProperty("parent_code")
	private String parentCode;
	/**
	 * 祖区划编号
	 */
	@Parameter(name = "ancestors", description = "祖区划编号")
	@JsonProperty("ancestors")
	private String ancestors;
	/**
	 * 区划名称
	 */
	@Parameter(name = "name", description = "区划名称")
	@JsonProperty("name")
	private String name;
	/**
	 * 省级区划编号
	 */
	@Parameter(name = "province_code", description = "省级区划编号")
	@JsonProperty("province_code")
	private String provinceCode;
	/**
	 * 省级名称
	 */
	@Parameter(name = "province_name", description = "省级名称")
	@JsonProperty("province_name")
	private String provinceName;
	/**
	 * 市级区划编号
	 */
	@Parameter(name = "city_code", description = "市级区划编号")
	@JsonProperty("city_code")
	private String cityCode;
	/**
	 * 市级名称
	 */
	@Parameter(name = "city_name", description = "市级名称")
	@JsonProperty("city_name")
	private String cityName;
	/**
	 * 区级区划编号
	 */
	@Parameter(name = "district_code", description = "区级区划编号")
	@JsonProperty("district_code")
	private String districtCode;
	/**
	 * 区级名称
	 */
	@Parameter(name = "district_name", description = "区级名称")
	@JsonProperty("district_name")
	private String districtName;
	/**
	 * 镇级区划编号
	 */
	@Parameter(name = "town_code", description = "镇级区划编号")
	@JsonProperty("town_code")
	private String townCode;
	/**
	 * 镇级名称
	 */
	@Parameter(name = "town_name", description = "镇级名称")
	@JsonProperty("town_name")
	private String townName;
	/**
	 * 村级区划编号
	 */
	@Parameter(name = "village_code", description = "村级区划编号")
	@JsonProperty("village_code")
	private String villageCode;
	/**
	 * 村级名称
	 */
	@Parameter(name = "village_name", description = "村级名称")
	@JsonProperty("village_name")
	private String villageName;
	/**
	 * 层级
	 */
	@Parameter(name = "level", description = "层级")
	@JsonProperty("level")
	private Integer level;
	/**
	 * 排序
	 */
	@Parameter(name = "sort", description = "排序")
	@JsonProperty("sort")
	private Integer sort;
	/**
	 * 备注
	 */
	@Parameter(name = "remark", description = "备注")
	@JsonProperty("remark")
	private String remark;
}
