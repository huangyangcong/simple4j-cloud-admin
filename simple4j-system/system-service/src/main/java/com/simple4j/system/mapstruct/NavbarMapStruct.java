package com.simple4j.system.mapstruct;

import com.simple4j.api.base.Page;
import com.simple4j.system.entity.Navbar;
import com.simple4j.system.request.NavbarAddOrUpdateRequest;
import com.simple4j.system.request.NavbarAddRequest;
import com.simple4j.system.request.NavbarUpdateRequest;
import com.simple4j.system.response.NavbarDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * 数据转换类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NavbarMapStruct {

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	Navbar toPo(NavbarAddRequest vo);

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	Navbar toPo(NavbarAddOrUpdateRequest vo);

	/**
	 * VO转PO
	 *
	 * @return
	 * @vo vo
	 */
	Navbar toPo(NavbarUpdateRequest vo);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	NavbarDetailResponse toVo(Navbar po);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	List<NavbarDetailResponse> toVo(List<Navbar> po);

	/**
	 * 分页转换PO转VO
	 *
	 * @param po
	 * @return
	 */
	Page<NavbarDetailResponse> toVo(Page<Navbar> po);
}
