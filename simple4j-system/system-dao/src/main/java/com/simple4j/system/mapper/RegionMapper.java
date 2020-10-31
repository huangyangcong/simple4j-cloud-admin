package com.simple4j.system.mapper;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.system.entity.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 行政区划表 Mapper 接口
 *
 * @author hyc
 */
public interface RegionMapper extends ExtendMapper<Region> {

  /**
   * 懒加载列表
   *
   * @param parentCode
   * @param code
   * @param name
   * @return
   */
  List<Region> lazyList(
      @Param("parentCode") String parentCode,
      @Param("code") String code,
      @Param("name") String name);

  /**
   * 查询详情
   *
   * @param code
   * @return
   */
  Region detail(@Param("code") String code);
}
