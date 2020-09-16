package com.simple4j.user.base;

import java.util.List;

import lombok.Data;


/**
 * 分页结果.
 *
 * @author hyc
 */
@Data
public class Page<T> {

	private long pageNo = 1;

	private long pageSize = 10;

	/**
	 * 总数.
	 */
	private long total;
	/**
	 * 分页数.
	 */
	private long pages;
	/**
	 * 结果集.
	 */
	private List<T> results;

	public Page(long pageNo, long pageSize, long total, List<T> results) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.total = total;
		this.results = results;
	}
}
