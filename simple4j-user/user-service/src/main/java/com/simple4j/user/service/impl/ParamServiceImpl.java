package com.simple4j.user.service.impl;

import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4j.user.service.IParamService;
import lombok.RequiredArgsConstructor;
import com.simple4j.user.entity.Param;
import com.simple4j.user.dao.ParamMapper;
import com.simple4j.user.mapstruct.ParamMapStruct;
import com.simple4j.user.request.ParamAddOrUpdateRequest;
import com.simple4j.user.request.ParamAddRequest;
import com.simple4j.user.request.ParamDetailRequest;
import com.simple4j.user.request.ParamListRequest;
import com.simple4j.user.request.ParamPageRequest;
import com.simple4j.user.request.ParamRemoveRequest;
import com.simple4j.user.request.ParamUpdateRequest;
import com.simple4j.user.response.ParamDetailResponse;
import com.simple4j.user.service.IParamService;

import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@RequiredArgsConstructor
public class ParamServiceImpl implements IParamService {

	private final ParamMapStruct paramMapStruct;

	@Override
	public ParamDetailResponse detail(ParamDetailRequest paramDetailRequest) {
		Param detail = getOne(
			Wrappers.<Param>lambdaQuery().eq(Param::getId, paramDetailRequest.getId()));
		return paramMapStruct.toVo(detail);
	}

	@Override
	public List<ParamDetailResponse> list(ParamListRequest paramListRequest) {
		LambdaQueryWrapper<Param> queryWrapper = Wrappers.<Param>lambdaQuery();
		List<Param> pages = list(queryWrapper);
		return paramMapStruct.toVo(pages);
	}

	@Override
	public Page<ParamDetailResponse> page(ParamPageRequest paramPageRequest) {
		LambdaQueryWrapper<Param> queryWrapper = Wrappers.<Param>lambdaQuery()
			.eq(StrUtil.isNotEmpty(paramPageRequest.getParamName()), Param::getParamName,
				paramPageRequest.getParamName())
			.eq(StrUtil.isNotEmpty(paramPageRequest.getParamKey()), Param::getParamKey,
				paramPageRequest.getParamKey())
			.eq(StrUtil.isNotEmpty(paramPageRequest.getParamValue()), Param::getParamValue,
				paramPageRequest.getParamValue());
		Page<Param> pages = page(
			new Page<>(paramPageRequest.getPageNo(), paramPageRequest.getPageSize()), queryWrapper);
		return paramMapStruct.toVo(pages);
	}

	@Override
	public void add(ParamAddRequest paramAddRequest) {
		save(paramMapStruct.toPo(paramAddRequest));
	}

	@Override
	public void update(ParamUpdateRequest paramUpdateRequest) {
		updateById(paramMapStruct.toPo(paramUpdateRequest));
	}

	@Override
	public void addOrUpdate(ParamAddOrUpdateRequest paramAddOrUpdateRequest) {
		saveOrUpdate(paramMapStruct.toPo(paramAddOrUpdateRequest));
	}

	@Override
	public void remove(ParamRemoveRequest paramRemoveRequest) {
		removeByIds(paramRemoveRequest.getIds());
	}
}
