package com.simple4j.system.mapstruct;

import java.util.List;

import com.simple4j.api.base.Page;
import com.simple4j.system.entity.Region;
import com.simple4j.system.request.RegionAddRequest;
import com.simple4j.system.response.RegionDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegionMapStruct {

	/**
	 * @param regions
	 * @return
	 */
	List<RegionDetailResponse> toVo(List<Region> regions);

	/**
	 * @param regions
	 * @return
	 */
	RegionDetailResponse toVo(Region region);

	/**
	 * @param regions
	 * @return
	 */
	Page<RegionDetailResponse> toVo(Page<Region> region);

	/**
	 * @param regionAddRequest
	 * @return
	 */
	Region toPo(RegionAddRequest regionAddRequest);
}
