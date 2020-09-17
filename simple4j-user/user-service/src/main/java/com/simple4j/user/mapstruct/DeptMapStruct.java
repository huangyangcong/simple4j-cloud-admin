package com.simple4j.user.mapstruct;

import java.util.List;

import com.simple4j.api.base.Page;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.simple4j.user.entity.Dept;
import com.simple4j.user.request.DeptAddRequest;
import com.simple4j.user.request.DeptUpdateRequest;
import com.simple4j.user.response.DeptDetailResponse;

/**
 * 部门表数据转换类
 *
 * @author Blade
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
