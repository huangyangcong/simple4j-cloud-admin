package com.simple4j.system.mapstruct;

import com.simple4j.api.base.Page;
import com.simple4j.system.entity.Dept;
import com.simple4j.system.request.DeptAddRequest;
import com.simple4j.system.request.DeptUpdateRequest;
import com.simple4j.system.response.DeptDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * 部门表数据转换类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapStruct {

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	Dept toPo(DeptAddRequest vo);

	/**
	 * VO转PO
	 *
	 * @return
	 * @vo vo
	 */
	Dept toPo(DeptUpdateRequest vo);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	DeptDetailResponse toVo(Dept po);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	List<DeptDetailResponse> toVo(List<Dept> po);

	/**
	 * 分页转换PO转VO
	 *
	 * @param po
	 * @return
	 */
	Page<DeptDetailResponse> toVo(Page<Dept> po);
}
