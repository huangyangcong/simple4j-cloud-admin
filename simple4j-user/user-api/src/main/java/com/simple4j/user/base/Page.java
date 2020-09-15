package com.simple4j.user.base;

import java.util.List;


/**
 * 分页结果.
 *
 * @author hyc
 */
public class Page<T> {

	private int pageNo = 1;

	private int pageSize = 10;

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

	public Page(int pageNo, int pageSize, int total, List<T> results) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.total = total;
		this.results = results;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
