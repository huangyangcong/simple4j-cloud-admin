package com.simple4j.system.mapstruct;

import java.util.List;

import com.simple4j.api.base.Page;
import com.simple4j.system.entity.Dict;
import com.simple4j.system.request.DictAddOrUpdateRequest;
import com.simple4j.system.response.DictDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 字典表数据转换类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictMapStruct {

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	Dict toPo(DictAddOrUpdateRequest vo);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	DictDetailResponse toVo(Dict po);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	List<DictDetailResponse> toVo(List<Dict> po);

	/**
	 * 分页转换PO转VO
	 *
	 * @param po
	 * @return
	 */
	Page<DictDetailResponse> toVo(Page<Dict> po);
}
