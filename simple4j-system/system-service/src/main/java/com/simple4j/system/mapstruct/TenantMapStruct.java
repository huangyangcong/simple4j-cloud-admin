package com.simple4j.system.mapstruct;

import com.simple4j.api.base.Page;
import com.simple4j.system.entity.Tenant;
import com.simple4j.system.request.TenantAddOrUpdateRequest;
import com.simple4j.system.request.TenantUpdateRequest;
import com.simple4j.system.response.TenantDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * 租户表数据转换类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TenantMapStruct {

  /**
   * VO转PO
   *
   * @param vo
   * @return
   */
  Tenant toPo(TenantAddOrUpdateRequest vo);

  /**
   * VO转PO
   *
   * @return
   * @vo vo
   */
  Tenant toPo(TenantUpdateRequest vo);

  /**
   * PO转VO
   *
   * @param po
   * @return
   */
  TenantDetailResponse toVo(Tenant po);

  /**
   * PO转VO
   *
   * @param po
   * @return
   */
  List<TenantDetailResponse> toVo(List<Tenant> po);

  /**
   * 分页转换PO转VO
   *
   * @param po
   * @return
   */
  Page<TenantDetailResponse> toVo(Page<Tenant> po);
}
