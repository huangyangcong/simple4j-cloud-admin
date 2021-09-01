package com.simple4j.system.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.BusinessException;
import com.simple4j.api.base.Page;
import com.simple4j.system.common.constant.CacheNames;
import com.simple4j.system.entity.Dict;
import com.simple4j.system.mapper.DictMapper;
import com.simple4j.system.mapstruct.DictMapStruct;
import com.simple4j.system.request.DictAddOrUpdateRequest;
import com.simple4j.system.request.DictDetailRequest;
import com.simple4j.system.request.DictListRequest;
import com.simple4j.system.request.DictPageRequest;
import com.simple4j.system.request.DictRemoveRequest;
import com.simple4j.system.response.DictDetailResponse;
import com.simple4j.system.service.IDictService;
import com.simple4j.system.util.TreeUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author hyc
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl implements IDictService {

	private final DictMapper dictMapper;
	private final DictMapStruct dictMapStruct;

	@Override
	public List<DictDetailResponse> tree() {
		return TreeUtil.buildTree(dictMapStruct.toVo(dictMapper.tree()));
	}

	@Override
	@Cacheable(cacheNames = CacheNames.DICT_VALUE, key = "#code+'_'+#dictKey")
	public String getValue(String code, Integer dictKey) {
		return StrUtil.nullToDefault(dictMapper.getValue(code, dictKey), StrUtil.EMPTY);
	}

	@Override
	@Cacheable(cacheNames = CacheNames.DICT_LIST, key = "#code")
	public List<DictDetailResponse> getList(String code) {
		List<Dict> list = dictMapper.getList(code);
		return dictMapStruct.toVo(list);
	}

	@Override
	@CacheEvict(
		cacheNames = {CacheNames.DICT_LIST, CacheNames.DICT_VALUE},
		allEntries = true)
	public boolean submit(DictAddOrUpdateRequest dictAddOrUpdateRequest) {
		LambdaQueryWrapper<Dict> lqw =
			Wrappers.<Dict>query()
				.lambda()
				.eq(Dict::getCode, dictAddOrUpdateRequest.getCode())
				.eq(Dict::getDictKey, dictAddOrUpdateRequest.getDictKey());
		Integer cnt =
			dictMapper.selectCount(
				(ObjectUtil.isEmpty(dictAddOrUpdateRequest.getId()))
					? lqw
					: lqw.notIn(Dict::getId, dictAddOrUpdateRequest.getId()));
		if (cnt > 0) {
			throw new BusinessException("当前字典键值已存在!");
		}
		return dictMapper.saveOrUpdate(dictMapStruct.toPo(dictAddOrUpdateRequest));
	}

	@Override
	public DictDetailResponse detail(DictDetailRequest dictDetailRequest) {
		Dict detail =
			dictMapper
				.getOne(Wrappers.<Dict>lambdaQuery().eq(Dict::getId, dictDetailRequest.getId()));
		return dictMapStruct.toVo(detail);
	}

	@Override
	public List<DictDetailResponse> list(DictListRequest dictListRequest) {
		Wrapper<Dict> queryWrapper =
			Wrappers.<Dict>lambdaQuery()
				.eq(
					StrUtil.isNotEmpty(dictListRequest.getCode()),
					Dict::getCode,
					dictListRequest.getCode())
				.eq(
					StrUtil.isNotEmpty(dictListRequest.getDictValue()),
					Dict::getDictValue,
					dictListRequest.getDictValue())
				.orderByAsc(Dict::getSort);
		List<Dict> pages = dictMapper.list(queryWrapper);
		return TreeUtil.buildTree(dictMapStruct.toVo(pages));
	}

	@Override
	public Page<DictDetailResponse> page(DictPageRequest dictPageRequest) {
		LambdaQueryWrapper<Dict> queryWrapper = Wrappers.<Dict>lambdaQuery()
			.orderByAsc(Dict::getSort);
		IPage<Dict> page =
			dictMapper.page(
				new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
					dictPageRequest.getPageNo(), dictPageRequest.getPageSize()),
				queryWrapper);
		Page<Dict> pages =
			new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
		return dictMapStruct.toVo(pages);
	}

	@CacheEvict(
		cacheNames = {CacheNames.DICT_LIST, CacheNames.DICT_VALUE},
		allEntries = true)
	@Override
	public boolean remove(DictRemoveRequest dictRemoveRequest) {
		return dictMapper.removeByIds(dictRemoveRequest.getIds());
	}
}
