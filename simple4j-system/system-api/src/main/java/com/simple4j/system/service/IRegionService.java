package com.simple4j.user.service;

import com.simple4j.api.base.Page;
import com.simple4j.user.request.*;
import com.simple4j.user.response.RegionDetailResponse;

import java.util.List;

/**
 * 行政区划表 服务类
 *
 * @author Chill
 */
public interface IRegionService {

	/**
	 * 提交
	 *
	 * @param region
	 * @return
	 */
	boolean submit(RegionAddRequest regionAddRequest);

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	boolean removeRegion(RegionRemoveRequest regionRemoveRequest);

	/**
	 * 懒加载列表
	 *
	 * @param parentCode
	 * @param code
	 * @param name
	 * @return
	 */
	List<RegionDetailResponse> lazyList(RegionLazyListRequest regionLazyListRequest);

	/**
	 * 查看行政区划详情
	 *
	 * @param regionDetailRequest
	 * @return
	 */
	RegionDetailResponse detail(RegionDetailRequest regionDetailRequest);

	/**
	 * 添加行政区划
	 *
	 * @param regionAddRequest
	 * @return
	 */
	boolean add(RegionAddRequest regionAddRequest);

	/**
	 * 更新行政区划
	 *
	 * @param regionUpdateRequest
	 * @return
	 */
	boolean update(RegionUpdateRequest regionUpdateRequest);

	/**
	 * 分页获取
	 *
	 * @param regionPageRequest
	 * @return
	 */
	Page<RegionDetailResponse> page(RegionPageRequest regionPageRequest);
}
