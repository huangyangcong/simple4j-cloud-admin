package com.simple4j.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.simple4j.user.entity.Code;

/**
 * 服务类
 *
 * @author Chill
 */
public interface ICodeService extends IService<Code> {

	/**
	 * 提交
	 *
	 * @param code
	 * @return
	 */
	boolean submit(Code code);

}
