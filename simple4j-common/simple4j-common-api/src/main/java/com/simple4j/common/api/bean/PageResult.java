package com.simple4j.common.api.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 分页结果
 *
 * @author hyc
 */
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

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
}
