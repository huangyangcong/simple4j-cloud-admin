package com.simple4j.user.base;

/**
 * @author hyc
 * @version 1.0.0
 */
public interface INode<T, K> extends Comparable<T> {

	/**
	 * 添加子节点
	 */
	void addChildren(T children);

	/**
	 * 获取id
	 *
	 * @return
	 */
	K getTid();

	/**
	 * 获取父节点id
	 *
	 * @return
	 */
	K getTParentId();
}

