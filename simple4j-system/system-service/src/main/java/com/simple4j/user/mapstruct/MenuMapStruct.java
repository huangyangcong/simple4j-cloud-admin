package com.simple4j.user.mapstruct;

import java.util.List;

import com.simple4j.api.base.Page;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.simple4j.user.entity.Menu;
import com.simple4j.user.request.MenuAddRequest;
import com.simple4j.user.request.MenuUpdateRequest;
import com.simple4j.user.response.MenuDetailResponse;

/**
 * 菜单表数据转换类
 *
 * @author hyc
 * @since 2020-08-25
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapStruct {

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	Menu toPo(MenuAddRequest vo);

	/**
	 * VO转PO
	 *
	 * @return
	 * @vo vo
	 */
	Menu toPo(MenuUpdateRequest vo);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	MenuDetailResponse toVo(Menu po);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	List<MenuDetailResponse> toVo(List<Menu> po);


	/**
	 * 分页转换PO转VO
	 *
	 * @param po
	 * @return
	 */
	Page<MenuDetailResponse> toVo(Page<Menu> po);
}
