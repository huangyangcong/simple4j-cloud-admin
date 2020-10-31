package com.simple4j.system.mapstruct;

import com.simple4j.api.base.Page;
import com.simple4j.system.entity.NavbarMenu;
import com.simple4j.system.request.NavbarMenuAddOrUpdateRequest;
import com.simple4j.system.request.NavbarMenuAddRequest;
import com.simple4j.system.request.NavbarMenuUpdateRequest;
import com.simple4j.system.response.NavbarMenuDetailResponse;
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
