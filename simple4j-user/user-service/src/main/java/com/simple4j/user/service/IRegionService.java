package com.simple4j.user.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.simple4j.user.entity.Region;
import com.simple4j.user.request.RegionAddRequest;
import com.simple4j.user.request.RegionDetailRequest;
import com.simple4j.user.request.RegionLazyListRequest;
import com.simple4j.user.request.RegionRemoveRequest;
import com.simple4j.user.request.RegionUpdateRequest;
import com.simple4j.user.response.RegionDetailResponse;

/**
 * 行政区划表 服务类
 *
 * @author Chill
 */
public interface IRegionService extends IService<Region> {

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
}
