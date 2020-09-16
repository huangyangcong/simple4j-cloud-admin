package com.simple4j.user.mapstruct;

import java.util.List;

import com.simple4j.user.base.Page;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.simple4j.user.entity.Navbar;
import com.simple4j.user.request.NavbarAddOrUpdateRequest;
import com.simple4j.user.request.NavbarAddRequest;
import com.simple4j.user.request.NavbarUpdateRequest;
import com.simple4j.user.response.NavbarDetailResponse;

/**
 * 数据转换类
 *
 * @author Blade
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
