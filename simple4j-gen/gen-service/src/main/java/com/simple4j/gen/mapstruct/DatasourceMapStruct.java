package com.simple4j.gen.mapstruct;

import java.util.List;

import com.simple4j.gen.entity.Datasource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.simple4j.gen.request.DatasourceAddRequest;
import com.simple4j.gen.request.DatasourceAddOrUpdateRequest;
import com.simple4j.gen.request.DatasourceUpdateRequest;
import com.simple4j.gen.response.DatasourceDetailResponse;
import com.simple4j.api.base.Page;
/**
 * 数据源配置表数据转换类
 *
 * @author Blade
 * @since 2020-09-19
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DatasourceMapStruct {

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	Datasource toPo(DatasourceAddRequest vo);

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	Datasource toPo(DatasourceAddOrUpdateRequest vo);

	/**
	 * VO转PO
	 *
	 * @vo vo
	 * @return
	 */
	Datasource toPo(DatasourceUpdateRequest vo);

	/**
	* PO转VO
	*
	* @param po
	* @return
	*/
	DatasourceDetailResponse toVo(Datasource po);

	/**
	* PO转VO
	*
	* @param po
	* @return
	*/
	List<DatasourceDetailResponse> toVo(List<Datasource> po);


	/**
	* 分页转换PO转VO
	* @param po
	* @return
	*/
	Page<DatasourceDetailResponse> toVo(Page<Datasource> po);
}
