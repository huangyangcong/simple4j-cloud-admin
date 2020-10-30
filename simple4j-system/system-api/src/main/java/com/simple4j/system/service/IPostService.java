package com.simple4j.system.service;

import java.util.List;
import java.util.Set;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.PostAddRequest;
import com.simple4j.system.request.PostDetailRequest;
import com.simple4j.system.request.PostListRequest;
import com.simple4j.system.request.PostPageRequest;
import com.simple4j.system.request.PostRemoveRequest;
import com.simple4j.system.request.PostUpdateRequest;
import com.simple4j.system.response.PostDetailResponse;

/**
 * 岗位表 服务类
 *
 * @author hyc
 */
public interface IPostService {

	/**
	 * 详情
	 */
	PostDetailResponse detail(PostDetailRequest postDetailRequest);

	/**
	 * 获取岗位ID
	 *
	 * @param tenantId
	 * @param postNames
	 * @return
	 */
	Set<String> getPostIds(String tenantId, List<String> postNames);

	/**
	 * 获取岗位名
	 *
	 * @param userId
	 * @return
	 */
	List<String> getPostNames(String userId);


	/**
	 * 列表 岗位表
	 */
	List<PostDetailResponse> list(PostListRequest postListRequest);

	/**
	 * 自定义分页 岗位表
	 */
	Page<PostDetailResponse> page(PostPageRequest postPageRequest);

	/**
	 * 新增 岗位表
	 */
	boolean add(PostAddRequest postAddRequest);

	/**
	 * 修改 岗位表
	 */
	boolean update(PostUpdateRequest postUpdateRequest);

	/**
	 * 删除 岗位表
	 */
	boolean remove(PostRemoveRequest postRemoveRequest);

}
