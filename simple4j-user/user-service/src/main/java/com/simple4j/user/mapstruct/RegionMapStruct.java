package com.simple4j.user.mapstruct;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.simple4j.user.entity.Region;
import com.simple4j.user.request.RegionAddRequest;
import com.simple4j.user.response.RegionDetailResponse;

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
