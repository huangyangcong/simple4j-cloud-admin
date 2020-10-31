package com.simple4j.gen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.api.base.Page;
import com.simple4j.gen.entity.Datasource;
import com.simple4j.gen.mapper.DatasourceMapper;
import com.simple4j.gen.mapstruct.DatasourceMapStruct;
import com.simple4j.gen.request.DatasourceAddOrUpdateRequest;
import com.simple4j.gen.request.DatasourceAddRequest;
import com.simple4j.gen.request.DatasourceDetailRequest;
import com.simple4j.gen.request.DatasourceListRequest;
import com.simple4j.gen.request.DatasourcePageRequest;
import com.simple4j.gen.request.DatasourceRemoveRequest;
import com.simple4j.gen.request.DatasourceUpdateRequest;
import com.simple4j.gen.response.DatasourceDetailResponse;
import com.simple4j.gen.service.IDatasourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 数据源配置表 服务实现类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Service
@RequiredArgsConstructor
public class DatasourceServiceImpl implements IDatasourceService {

	private final DatasourceMapStruct datasourceMapStruct;
	private final DatasourceMapper datasourceMapper;

	@Override
	public DatasourceDetailResponse detail(DatasourceDetailRequest datasourceDetailRequest) {
		Datasource detail = datasourceMapper.getOne(
			Wrappers.<Datasource>lambdaQuery().eq(Datasource::getId, datasourceDetailRequest.getId()));
		return datasourceMapStruct.toVo(detail);
	}

	@Override
	public List<DatasourceDetailResponse> list(DatasourceListRequest datasourceListRequest) {
		LambdaQueryWrapper<Datasource> queryWrapper = Wrappers.<Datasource>lambdaQuery();
		List<Datasource> list = datasourceMapper.list(queryWrapper);
		return datasourceMapStruct.toVo(list);
	}

	@Override
	public Page<DatasourceDetailResponse> page(DatasourcePageRequest datasourcePageRequest) {
		LambdaQueryWrapper<Datasource> queryWrapper = Wrappers.<Datasource>lambdaQuery();
		IPage<Datasource> page = datasourceMapper.page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(datasourcePageRequest.getPageNo(), datasourcePageRequest.getPageSize()), queryWrapper);
		Page<Datasource> pages = new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
		return datasourceMapStruct.toVo(pages);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean add(DatasourceAddRequest datasourceAddRequest) {
		return datasourceMapper.save(datasourceMapStruct.toPo(datasourceAddRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean update(DatasourceUpdateRequest datasourceUpdateRequest) {
		return datasourceMapper.updateByIdBool(datasourceMapStruct.toPo(datasourceUpdateRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean addOrUpdate(DatasourceAddOrUpdateRequest datasourceAddOrUpdateRequest) {
		return datasourceMapper.saveOrUpdate(datasourceMapStruct.toPo(datasourceAddOrUpdateRequest));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean remove(DatasourceRemoveRequest datasourceRemoveRequest) {
		return datasourceMapper.physicsDeleteBatchByIdsBool(datasourceRemoveRequest.getIds());
	}
}
