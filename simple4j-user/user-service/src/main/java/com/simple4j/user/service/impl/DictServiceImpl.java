package com.simple4j.user.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4j.autoconfigure.util.TreeUtil;
import com.simple4j.user.common.constant.CacheNames;
import com.simple4j.user.service.IDictService;
import lombok.RequiredArgsConstructor;
import com.simple4j.user.entity.Dict;
import com.simple4j.user.dao.DictMapper;
import com.simple4j.user.mapstruct.DictMapStruct;
import com.simple4j.user.request.DictAddOrUpdateRequest;
import com.simple4j.user.request.DictDetailRequest;
import com.simple4j.user.request.DictListRequest;
import com.simple4j.user.request.DictPageRequest;
import com.simple4j.user.request.DictRemoveRequest;
import com.simple4j.user.response.DictDetailResponse;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

	private final DictMapStruct dictMapStruct;

	@Override
	public List<DictDetailResponse> tree() {
		return TreeUtil.buildTree(dictMapStruct.toVo(baseMapper.tree()));
	}

	@Override
	@Cacheable(cacheNames = CacheNames.DICT_VALUE, key = "#code+'_'+#dictKey")
	public String getValue(String code, Integer dictKey) {
		return StrUtil.nullToDefault(baseMapper.getValue(code, dictKey), StrUtil.EMPTY);
	}

	@Override
	@Cacheable(cacheNames = CacheNames.DICT_LIST, key = "#code")
	public List<DictDetailResponse> getList(String code) {
		List<Dict> list = baseMapper.getList(code);
		return dictMapStruct.toVo(list);
	}

	@Override
	@CacheEvict(cacheNames = {CacheNames.DICT_LIST, CacheNames.DICT_VALUE}, allEntries = true)
	public boolean submit(DictAddOrUpdateRequest dictAddOrUpdateRequest) {
		LambdaQueryWrapper<Dict> lqw = Wrappers.<Dict>query().lambda()
			.eq(Dict::getCode, dictAddOrUpdateRequest.getCode())
			.eq(Dict::getDictKey, dictAddOrUpdateRequest.getDictKey());
		Integer cnt = baseMapper.selectCount(
			(ObjectUtil.isEmpty(dictAddOrUpdateRequest.getId())) ? lqw
				: lqw.notIn(Dict::getId, dictAddOrUpdateRequest.getId()));
		if (cnt > 0) {
			throw new ApiException("当前字典键值已存在!");
		}
		return saveOrUpdate(dictMapStruct.toPo(dictAddOrUpdateRequest));
	}

	@Override
	public DictDetailResponse detail(DictDetailRequest dictDetailRequest) {
		Dict detail = getOne(
			Wrappers.<Dict>lambdaQuery().eq(Dict::getId, dictDetailRequest.getId()));
		return dictMapStruct.toVo(detail);
	}

	@Override
	public List<DictDetailResponse> list(DictListRequest dictListRequest) {
		Wrapper<Dict> queryWrapper = Wrappers.<Dict>lambdaQuery()
			.eq(StrUtil.isNotEmpty(dictListRequest.getCode()), Dict::getCode,
				dictListRequest.getCode())
			.eq(StrUtil.isNotEmpty(dictListRequest.getDictValue()), Dict::getDictValue,
				dictListRequest.getDictValue())
			.orderByAsc(Dict::getSort);
		List<Dict> pages = list(queryWrapper);
		return TreeUtil.buildTree(dictMapStruct.toVo(pages));
	}

	@Override
	public Page<DictDetailResponse> page(DictPageRequest dictPageRequest) {
		LambdaQueryWrapper<Dict> queryWrapper = Wrappers.<Dict>lambdaQuery()
			.orderByAsc(Dict::getSort);
		Page<Dict> pages = page(
			new Page<>(dictPageRequest.getPageNo(), dictPageRequest.getPageSize()), queryWrapper);
		return dictMapStruct.toVo(pages);
	}

	@Override
	public void remove(DictRemoveRequest dictRemoveRequest) {
		removeByIds(dictRemoveRequest.getIds());
	}
}
