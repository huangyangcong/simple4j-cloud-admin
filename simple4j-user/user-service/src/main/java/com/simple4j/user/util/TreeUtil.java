package com.simple4j.user.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.simple4j.api.base.INode;

/**
 * @Date 2020/4/14 13:44
 * @Created by hyc
 */
public class TreeUtil {


	public static <E, F extends INode<F, E>> List<F> buildTree(List<F> list) {
		if (null == list || list.isEmpty()) {
			return null;
		}
		List<F> treeList = new ArrayList<>();
		Map<E, F> map = list.stream().collect(Collectors.toMap(INode::getTid, Function.identity()));
		for (F tree : list) {
			if (null == map.get(tree.getTParentId())) {
				treeList.add(tree);
			} else {
				// 子级通过父id获取到父级的类型
				F parent = map.get(tree.getTParentId());
				// 父级获得子级，再将子级放到对应的父级中
				parent.addChildren(tree);
			}
		}
		return treeList;
	}
}
