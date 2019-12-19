package com.simple4j.common.api.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 分页结果
 *
 * @author hyc
 */
@Data
public class PageResult<T> extends BasePage {
	private static final long serialVersionUID = 1026742762164981481L;
	/**
	 * 总数
	 */
	@JSONField(ordinal = 0)
	private long total;
	/**
	 * 分页数
	 */
	@JSONField(ordinal = 1)
	private long pages;
	/**
	 * 结果集
	 */
	@JSONField(ordinal = 0)
	private List<T> results;

	public PageResult(int pageNo, int pageSize) {
		super(pageNo, pageSize);

	}
}
