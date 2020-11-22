package com.simple4j.system.mapstruct;

import java.util.List;

import com.simple4j.api.base.Page;
import com.simple4j.system.entity.UserOauth;
import com.simple4j.system.request.UserOauthAddRequest;
import com.simple4j.system.request.UserOauthUpdateRequest;
import com.simple4j.system.response.UserOauthAddOrUpdateRequest;
import com.simple4j.system.response.UserOauthDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 用户第三方认证表数据转换类
 *
 * @author hyc
 * @since 2020-09-16
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserOauthMapStruct {

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	UserOauth toPo(UserOauthAddRequest vo);

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	UserOauth toPo(UserOauthAddOrUpdateRequest vo);

	/**
	 * VO转PO
	 *
	 * @return
	 * @vo vo
	 */
	UserOauth toPo(UserOauthUpdateRequest vo);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	UserOauthDetailResponse toVo(UserOauth po);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	List<UserOauthDetailResponse> toVo(List<UserOauth> po);

	/**
	 * 分页转换PO转VO
	 *
	 * @param po
	 * @return
	 */
	Page<UserOauthDetailResponse> toVo(Page<UserOauth> po);
}
