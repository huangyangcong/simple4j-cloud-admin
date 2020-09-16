package com.simple4j.user.mapstruct;

import java.util.List;

import com.simple4j.user.base.Page;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.simple4j.user.entity.NavbarMenu;
import com.simple4j.user.request.NavbarMenuAddOrUpdateRequest;
import com.simple4j.user.request.NavbarMenuAddRequest;
import com.simple4j.user.request.NavbarMenuUpdateRequest;
import com.simple4j.user.response.NavbarMenuDetailResponse;

/**
 * 数据转换类
 *
 * @author Blade
 * @since 2020-08-26
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NavbarMenuMapStruct {

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	NavbarMenu toPo(NavbarMenuAddRequest vo);

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	NavbarMenu toPo(NavbarMenuAddOrUpdateRequest vo);

	/**
	 * VO转PO
	 *
	 * @return
	 * @vo vo
	 */
	NavbarMenu toPo(NavbarMenuUpdateRequest vo);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	NavbarMenuDetailResponse toVo(NavbarMenu po);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	List<NavbarMenuDetailResponse> toVo(List<NavbarMenu> po);


	/**
	 * 分页转换PO转VO
	 *
	 * @param po
	 * @return
	 */
	Page<NavbarMenuDetailResponse> toVo(Page<NavbarMenu> po);
}
