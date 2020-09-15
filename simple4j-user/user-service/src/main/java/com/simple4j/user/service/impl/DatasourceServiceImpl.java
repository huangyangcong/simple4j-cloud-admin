package com.simple4j.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4j.user.service.IDatasourceService;
import com.simple4j.user.entity.Datasource;
import com.simple4j.user.dao.DatasourceMapper;
import com.simple4j.user.service.IDatasourceService;

import org.springframework.stereotype.Service;

/**
 * 数据源配置表 服务实现类
 *
 * @author Chill
 */
@Service
public class DatasourceServiceImpl extends ServiceImpl<DatasourceMapper, Datasource> implements
		IDatasourceService {

}
