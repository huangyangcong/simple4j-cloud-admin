package com.simple4j.system.mapper;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.system.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 岗位表 Mapper 接口
 *
 * @author hyc
 */
public interface PostMapper extends ExtendMapper<Post> {

  /**
   * 获取岗位名
   *
   * @param userId
   * @return
   */
  List<String> getPostNames(@Param("userId") String userId);
}
