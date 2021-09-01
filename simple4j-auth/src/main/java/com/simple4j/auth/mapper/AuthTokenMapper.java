package com.simple4j.auth.mapper;

import com.simple4j.auth.entity.AuthToken;
import com.simple4j.auth.entity.UserRole;
import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author hyc
 */
public interface AuthTokenMapper extends ExtendMapper<AuthToken> {

}
