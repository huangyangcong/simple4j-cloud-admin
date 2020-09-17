package com.simple4j.user.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.api.base.Page;
import com.simple4j.user.entity.Code;
import com.simple4j.user.mapper.CodeMapper;
import com.simple4j.user.mapstruct.CodeMapStruct;
import com.simple4j.user.request.CodeAddOrUpdateRequest;
import com.simple4j.user.request.CodeAddRequest;
import com.simple4j.user.request.CodeDetailRequest;
import com.simple4j.user.request.CodeListRequest;
import com.simple4j.user.request.CodePageRequest;
import com.simple4j.user.request.CodeRemoveRequest;
import com.simple4j.user.request.CodeUpdateRequest;
import com.simple4j.user.response.CodeDetailResponse;
import com.simple4j.user.service.ICodeService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 代码生成表 服务实现类
 *
 * @author Blade
 * @since 2020-09-16
 */
@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements ICodeService {

	private final CodeMapStruct codeMapStruct;
	private final CodeMapper codeMapper;

	@Override
	public CodeDetailResponse detail(CodeDetailRequest codeDetailRequest) {
		Code detail = codeMapper.getOne(
				Wrappers.<Code>lambdaQuery().eq(Code::getId, codeDetailRequest.getId()));
		return codeMapStruct.toVo(detail);
	}

	@Override
	public List<CodeDetailResponse> list(CodeListRequest codeListRequest) {
		LambdaQueryWrapper<Code> queryWrapper = Wrappers.<Code>lambdaQuery();
		List<Code> list = codeMapper.list(queryWrapper);
		return codeMapStruct.toVo(list);
	}

	@Override
	public Page<CodeDetailResponse> page(CodePageRequest codePageRequest) {
		LambdaQueryWrapper<Code> queryWrapper = Wrappers.<Code>lambdaQuery();
		IPage<Code> page = codeMapper
				.page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
						codePageRequest.getPageNo(), codePageRequest.getPageSize()), queryWrapper);
		Page<Code> pages = new Page<>(page.getCurrent(), page.getSize(), page.getTotal(),
				page.getRecords());
		return codeMapStruct.toVo(pages);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean add(CodeAddRequest codeAddRequest) {
		return codeMapper.save(codeMapStruct.toPo(codeAddRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean update(CodeUpdateRequest codeUpdateRequest) {
		return codeMapper.updateByIdBool(codeMapStruct.toPo(codeUpdateRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean addOrUpdate(CodeAddOrUpdateRequest codeAddOrUpdateRequest) {
		return codeMapper.saveOrUpdate(codeMapStruct.toPo(codeAddOrUpdateRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean remove(CodeRemoveRequest codeRemoveRequest) {
		return codeMapper.physicsDeleteBatchByIdsBool(codeRemoveRequest.getIds());
	}
}
