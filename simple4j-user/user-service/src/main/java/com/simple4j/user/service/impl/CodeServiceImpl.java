package com.simple4j.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4j.user.service.ICodeService;
import com.simple4j.user.entity.Code;
import com.simple4j.user.dao.CodeMapper;
import com.simple4j.user.service.ICodeService;

import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
public class CodeServiceImpl implements ICodeService {

	@Override
	public boolean submit(Code code) {
		return saveOrUpdate(code);
	}

}
