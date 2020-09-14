package com.simple4j.user.mapstruct;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.simple4j.user.entity.Tenant;
import com.simple4j.user.request.TenantAddOrUpdateRequest;
import com.simple4j.user.request.TenantUpdateRequest;
import com.simple4j.user.response.TenantDetailResponse;

/**
 * 租户表数据转换类
 *
 * @author Blade
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
