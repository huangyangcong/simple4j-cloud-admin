package com.simple4j.user.mapstruct;

import java.util.List;

import com.simple4j.api.base.Page;
import com.simple4j.user.entity.Datasource;
import com.simple4j.user.request.DatasourceAddOrUpdateRequest;
import com.simple4j.user.request.DatasourceAddRequest;
import com.simple4j.user.request.DatasourceUpdateRequest;
import com.simple4j.user.response.DatasourceDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 数据源配置表数据转换类
 *
 * @author Blade
 * @since 2020-09-16
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
