package com.simple4j.gen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.api.base.Page;
import com.simple4j.autoconfigure.mybatis.base.BaseEntity;
import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.gen.entity.Code;
import com.simple4j.gen.mapper.CodeMapper;
import com.simple4j.gen.mapstruct.CodeMapStruct;
import com.simple4j.gen.request.CodeAddOrUpdateRequest;
import com.simple4j.gen.request.CodeAddRequest;
import com.simple4j.gen.request.CodeDetailRequest;
import com.simple4j.gen.request.CodeGenRequest;
import com.simple4j.gen.request.CodeListRequest;
import com.simple4j.gen.request.CodePageRequest;
import com.simple4j.gen.request.CodeRemoveRequest;
import com.simple4j.gen.request.CodeUpdateRequest;
import com.simple4j.gen.request.DatasourceDetailRequest;
import com.simple4j.gen.response.CodeDetailResponse;
import com.simple4j.gen.response.DatasourceDetailResponse;
import com.simple4j.gen.service.ICodeService;
import com.simple4j.gen.service.IDatasourceService;
import com.simple4j.gen.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.util.Collection;
import java.util.List;


/**
 * 代码生成表 服务实现类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements ICodeService {

	private final CodeMapStruct codeMapStruct;
	private final CodeMapper codeMapper;
	private final IDatasourceService datasourceService;

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
		LambdaQueryWrapper<Code> queryWrapper = Wrappers.<Code>lambdaQuery()
			.like(StrUtil.isNotEmpty(codePageRequest.getCodeName()), Code::getCodeName,
				codePageRequest.getCodeName())
			.like(StrUtil.isNotEmpty(codePageRequest.getTableName()), Code::getTableName,
				codePageRequest.getTableName());
		IPage<Code> page = codeMapper
			.page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
					codePageRequest.getPageNo(), codePageRequest.getPageSize()),
				queryWrapper);
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

	@Override
	public void codeGen(OutputStream outputStream, CodeGenRequest codeGenRequest) {
		Collection<Code> codes = codeMapper.listByIds(codeGenRequest.getIds());
		codes.forEach(code -> {
			CodeGenerator generator = new CodeGenerator();
			// 设置数据源
			DatasourceDetailRequest datasourceDetailRequest = new DatasourceDetailRequest();
			datasourceDetailRequest.setId(code.getDatasourceId());
			DatasourceDetailResponse datasource = datasourceService.detail(datasourceDetailRequest);
			generator.setDriverName(datasource.getDriverClass());
			generator.setUrl(datasource.getUrl());
			generator.setUsername(datasource.getUsername());
			generator.setPassword(datasource.getPassword());
			generator.setOutputStream(outputStream);
			// 设置基础配置
			generator.setSystemName(codeGenRequest.getSystem());
			generator.setGroupId(codeGenRequest.getGroupId());
			generator.setProjectName(codeGenRequest.getProjectName());
			generator.setModuleName(codeGenRequest.getModuleName());
			generator.setSuperEntityClass(BaseEntity.class);
			generator.setSuperMapperClass(ExtendMapper.class.getCanonicalName());
			generator.setPackageName(code.getPackageName());
			generator.setTablePrefix(StrUtil.split(code.getTablePrefix(), ","));
			generator.setIncludeTables(StrUtil.split(code.getTableName(), ","));
			generator.run();
		});
	}
}
