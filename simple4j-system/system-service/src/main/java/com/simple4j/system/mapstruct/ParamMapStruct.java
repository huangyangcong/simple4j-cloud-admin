package com.simple4j.system.mapstruct;

import java.util.List;

import com.simple4j.api.base.Page;
import com.simple4j.system.entity.Param;
import com.simple4j.system.request.ParamAddOrUpdateRequest;
import com.simple4j.system.request.ParamAddRequest;
import com.simple4j.system.request.ParamUpdateRequest;
import com.simple4j.system.response.ParamDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 参数表数据转换类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParamMapStruct {

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	Param toPo(ParamAddRequest vo);

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	Param toPo(ParamAddOrUpdateRequest vo);

	/**
	 * VO转PO
	 *
	 * @return
	 * @vo vo
	 */
	Param toPo(ParamUpdateRequest vo);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	ParamDetailResponse toVo(Param po);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	List<ParamDetailResponse> toVo(List<Param> po);

	/**
	 * 分页转换PO转VO
	 *
	 * @param po
	 * @return
	 */
	Page<ParamDetailResponse> toVo(Page<Param> po);
}
