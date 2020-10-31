package com.simple4j.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.api.base.BusinessException;
import com.simple4j.api.base.Page;
import com.simple4j.system.entity.Region;
import com.simple4j.system.mapper.RegionMapper;
import com.simple4j.system.mapstruct.RegionMapStruct;
import com.simple4j.system.request.RegionAddRequest;
import com.simple4j.system.request.RegionDetailRequest;
import com.simple4j.system.request.RegionLazyListRequest;
import com.simple4j.system.request.RegionPageRequest;
import com.simple4j.system.request.RegionRemoveRequest;
import com.simple4j.system.request.RegionUpdateRequest;
import com.simple4j.system.response.RegionDetailResponse;
import com.simple4j.system.service.IRegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行政区划表 服务实现类
 *
 * @author hyc
 */
@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements IRegionService {

  public static final int PROVINCE_LEVEL = 1;
  public static final int CITY_LEVEL = 2;
  public static final int DISTRICT_LEVEL = 3;
  public static final int TOWN_LEVEL = 4;
  public static final int VILLAGE_LEVEL = 5;

  private final RegionMapper regionMapper;
  private final RegionMapStruct regionMapStruct;

  @Override
  public boolean add(RegionAddRequest regionAddRequest) {
    Region region = regionMapStruct.toPo(regionAddRequest);
    return regionMapper.save(region);
  }

  @Override
  public boolean update(RegionUpdateRequest regionUpdateRequest) {
    Region region = regionMapStruct.toPo(regionUpdateRequest);
    return regionMapper.updateByIdBool(region);
  }

  @Override
  public RegionDetailResponse detail(RegionDetailRequest regionDetailRequest) {
    Region region = regionMapper.detail(regionDetailRequest.getCode());
    return regionMapStruct.toVo(region);
  }

  @Override
  public boolean submit(RegionAddRequest regionAddRequest) {
    Region region = regionMapStruct.toPo(regionAddRequest);
    Integer cnt =
        regionMapper.selectCount(
            Wrappers.<Region>query().lambda().eq(Region::getCode, region.getCode()));
    if (cnt > 0) {
      return regionMapper.updateByIdBool(region);
    }
    // 设置祖区划编号
    Region parent = regionMapper.selectById(region.getParentCode());
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
    return regionMapper.save(region);
  }

  @Override
  public boolean removeRegion(RegionRemoveRequest regionRemoveRequest) {
    String id = regionRemoveRequest.getId();
    Integer cnt =
        regionMapper.selectCount(Wrappers.<Region>query().lambda().eq(Region::getParentCode, id));
    if (cnt > 0) {
      throw new BusinessException("请先删除子节点!");
    }
    return regionMapper.removeById(id);
  }

  @Override
  public List<RegionDetailResponse> lazyList(RegionLazyListRequest regionLazyListRequest) {
    return regionMapStruct.toVo(
        regionMapper.lazyList(
            regionLazyListRequest.getParentCode(),
            regionLazyListRequest.getCode(),
            regionLazyListRequest.getName()));
  }

  @Override
  public Page<RegionDetailResponse> page(RegionPageRequest regionPageRequest) {
    IPage<Region> page =
        regionMapper.page(
            new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
                regionPageRequest.getPageNo(), regionPageRequest.getPageSize()),
            Wrappers.<Region>lambdaQuery().eq(Region::getCode, regionPageRequest.getCode()));
    Page<Region> pages =
        new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    return regionMapStruct.toVo(pages);
  }
}
