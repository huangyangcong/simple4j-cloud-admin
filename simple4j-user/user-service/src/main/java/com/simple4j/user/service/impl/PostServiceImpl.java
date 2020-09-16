package com.simple4j.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.user.base.Page;
import com.simple4j.user.mapper.PostMapper;
import com.simple4j.user.entity.Post;
import com.simple4j.user.mapstruct.PostMapStruct;
import com.simple4j.user.request.PostAddRequest;
import com.simple4j.user.request.PostDetailRequest;
import com.simple4j.user.request.PostListRequest;
import com.simple4j.user.request.PostPageRequest;
import com.simple4j.user.request.PostRemoveRequest;
import com.simple4j.user.request.PostUpdateRequest;
import com.simple4j.user.response.PostDetailResponse;
import com.simple4j.user.service.IDictService;
import com.simple4j.user.service.IPostService;
import com.simple4j.user.service.IUserPostService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 岗位表 服务实现类
 *
 * @author Chill
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService {

	private final PostMapper postMapper;
	private final PostMapStruct postMapStruct;
	private final IDictService dictService;
	private final IUserPostService userPostService;

	@Override
	public PostDetailResponse detail(
			PostDetailRequest postDetailRequest) {
		Post detail = postMapper.getOne(
				Wrappers.<Post>lambdaQuery().eq(Post::getId, postDetailRequest.getId()));
		PostDetailResponse postDetailResponse = postMapStruct.toVo(detail);
		String categoryName = dictService
				.getValue("post_category", postDetailResponse.getCategory());
		postDetailResponse.setCategoryName(categoryName);
		return postDetailResponse;
	}

	@Override
	public List<Long> getPostIds(String tenantId, List<String> postNames) {
		List<Post> postList = postMapper.selectList(
				Wrappers.<Post>query().lambda().eq(Post::getTenantId, tenantId)
						.in(Post::getPostName, postNames));
		if (postList != null && postList.size() > 0) {
			return postList.stream().map(Post::getId).distinct()
					.collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public List<String> getPostNames(Long userId) {
		return postMapper.getPostNames(userId);
	}

	@Override
	public List<PostDetailResponse> list(PostListRequest postListRequest) {
		List<Post> pages = postMapper.list(Wrappers.<Post>lambdaQuery()
				.eq(StrUtil.isNotEmpty(postListRequest.getCode()), Post::getPostCode,
						postListRequest.getCode())
				.eq(StrUtil.isNotEmpty(postListRequest.getName()), Post::getPostName,
						postListRequest.getName())
				.eq(StrUtil.isNotEmpty(postListRequest.getTenantId()), Post::getTenantId,
						postListRequest.getTenantId())
		);
		return postMapStruct.toVo(pages);
	}

	@Override
	public Page<PostDetailResponse> page(PostPageRequest postPageRequest) {
		LambdaQueryWrapper<Post> queryWrapper = Wrappers.<Post>lambdaQuery();
		IPage<Post> page = postMapper.page(
				new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
						postPageRequest.getPageNo(), postPageRequest.getPageSize()), queryWrapper);
		Page<Post> pages = new Page<>(page.getCurrent(), page.getSize(), page.getTotal(),
				page.getRecords());
		return postMapStruct.toVo(pages);
	}

	@Override
	public boolean add(PostAddRequest postAddRequest) {
		return postMapper.save(postMapStruct.toPo(postAddRequest));
	}

	@Override
	public boolean update(PostUpdateRequest postUpdateRequest) {
		return postMapper.updateByIdBool(postMapStruct.toPo(postUpdateRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean remove(PostRemoveRequest postRemoveRequest) {
		List<String> postIds = postRemoveRequest.getIds();
		postMapper.physicsDeleteBatchByIds(postIds);
		userPostService.removeByPostIds(postIds);
		return true;
	}
}
