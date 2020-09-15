package com.simple4j.user.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.api.exception.BusinessException;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4j.user.service.IRegionService;
import lombok.RequiredArgsConstructor;
import com.simple4j.user.entity.Region;
import com.simple4j.user.dao.RegionMapper;
import com.simple4j.user.mapstruct.RegionMapStruct;
import com.simple4j.user.request.RegionAddRequest;
import com.simple4j.user.request.RegionDetailRequest;
import com.simple4j.user.request.RegionLazyListRequest;
import com.simple4j.user.request.RegionRemoveRequest;
import com.simple4j.user.request.RegionUpdateRequest;
import com.simple4j.user.response.RegionDetailResponse;

import org.springframework.stereotype.Service;

/**
 * 行政区划表 服务实现类
 *
 * @author Chill
 */
@Service
@RequiredArgsConstructor
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {

	public static final int PROVINCE_LEVEL = 1;
	public static final int CITY_LEVEL = 2;
	public static final int DISTRICT_LEVEL = 3;
	public static final int TOWN_LEVEL = 4;
	public static final int VILLAGE_LEVEL = 5;

	private final RegionMapStruct regionMapStruct;

	@Override
	public boolean add(RegionAddRequest regionAddRequest) {
		Region region = regionMapStruct.toPo(regionAddRequest);
		return save(region);
	}

	@Override
	public boolean update(RegionUpdateRequest regionUpdateRequest) {
		Region region = regionMapStruct.toPo(regionUpdateRequest);
		return updateById(region);
	}

	@Override
	public RegionDetailResponse detail(RegionDetailRequest regionDetailRequest) {
		Region region = baseMapper.detail(regionDetailRequest.getCode());
		return regionMapStruct.toVo(region);
	}

	@Override
	public boolean submit(RegionAddRequest regionAddRequest) {
		Region region = regionMapStruct.toPo(regionAddRequest);
		Integer cnt = baseMapper
			.selectCount(Wrappers.<Region>query().lambda().eq(Region::getCode, region.getCode()));
		if (cnt > 0) {
			return this.updateById(region);
		}
		// 设置祖区划编号
		Region parent = baseMapper.selectById(region.getParentCode());
		if (ObjectUtil.isNotEmpty(parent) || StrUtil.isNotEmpty(parent.getCode())) {
			String ancestors = parent.getAncestors() + StrUtil.COMMA + parent.getCode();
			region.setAncestors(ancestors);
		}
		// 设置省、市、区、镇、村
		Integer level = region.getLevel();
		String code = region.getCode();
		String name = region.getName();
		if (level == PROVINCE_LEVEL) {
			region.setProvinceCode(code);
			region.setProvinceName(name);
		} else if (level == CITY_LEVEL) {
			region.setCityCode(code);
			region.setCityName(name);
		} else if (level == DISTRICT_LEVEL) {
			region.setDistrictCode(code);
			region.setDistrictName(name);
		} else if (level == TOWN_LEVEL) {
			region.setTownCode(code);
			region.setTownName(name);
		} else if (level == VILLAGE_LEVEL) {
			region.setVillageCode(code);
			region.setVillageName(name);
		}
		return this.save(region);
	}

	@Override
	public boolean removeRegion(RegionRemoveRequest regionRemoveRequest) {
		String id = regionRemoveRequest.getId();
		Integer cnt = baseMapper
			.selectCount(Wrappers.<Region>query().lambda().eq(Region::getParentCode, id));
		if (cnt > 0) {
			throw new BusinessException("请先删除子节点!");
		}
		return removeById(id);
	}

	@Override
	public List<RegionDetailResponse> lazyList(RegionLazyListRequest regionLazyListRequest) {
		return regionMapStruct.toVo(baseMapper.lazyList(regionLazyListRequest.getParentCode(),
			regionLazyListRequest.getCode()
			, regionLazyListRequest.getName()));
	}
}
