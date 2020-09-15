package com.simple4j.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4j.user.service.IDictService;
import com.simple4j.user.service.IPostService;
import com.simple4j.user.service.IUserPostService;
import lombok.RequiredArgsConstructor;
import com.simple4j.user.entity.Post;
import com.simple4j.user.dao.PostMapper;
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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 岗位表 服务实现类
 *
 * @author Chill
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

	private final PostMapStruct postMapStruct;
	private final IDictService dictService;
	private final IUserPostService userPostService;

	@Override
	public PostDetailResponse detail(
		PostDetailRequest postDetailRequest) {
		Post detail = getOne(
			Wrappers.<Post>lambdaQuery().eq(Post::getId, postDetailRequest.getId()));
		PostDetailResponse postDetailResponse = postMapStruct.toVo(detail);
		String categoryName = dictService
			.getValue("post_category", postDetailResponse.getCategory());
		postDetailResponse.setCategoryName(categoryName);
		return postDetailResponse;
	}

	@Override
	public List<Long> getPostIds(String tenantId, List<String> postNames) {
		List<Post> postList = baseMapper.selectList(
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
		return baseMapper.getPostNames(userId);
	}

	@Override
	public List<PostDetailResponse> list(PostListRequest postListRequest) {
		List<Post> pages = list(Wrappers.<Post>lambdaQuery()
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
		Page<Post> pages = page(
			new Page<>(postPageRequest.getPageNo(), postPageRequest.getPageSize()), queryWrapper);
		return postMapStruct.toVo(pages);
	}

	@Override
	public void add(PostAddRequest postAddRequest) {
		save(postMapStruct.toPo(postAddRequest));
	}

	@Override
	public void update(PostUpdateRequest postUpdateRequest) {
		updateById(postMapStruct.toPo(postUpdateRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void remove(PostRemoveRequest postRemoveRequest) {
		List<String> postIds = postRemoveRequest.getIds();
		baseMapper.physicsDeleteBatchByIds(postIds);
		userPostService.removeByPostIds(postIds);
	}
}
