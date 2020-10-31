package com.simple4j.system.controller;

import com.simple4j.api.base.Page;
import com.simple4j.system.request.TenantAddOrUpdateRequest;
import com.simple4j.system.request.TenantDetailRequest;
import com.simple4j.system.request.TenantListRequest;
import com.simple4j.system.request.TenantPageRequest;
import com.simple4j.system.request.TenantRemoveRequest;
import com.simple4j.system.response.TenantDetailResponse;
import com.simple4j.system.service.ITenantService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 控制器
 *
 * @author hyc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tenant")
@Api(value = "租户管理", tags = "接口")
public class TenantController {

  private ITenantService tenantService;

  /** 详情 */
  @PostMapping("/detail")
  @ApiOperation(value = "详情", notes = "传入tenant")
  public ApiResponse<TenantDetailResponse> detail(
      @Valid @RequestBody TenantDetailRequest tenantDetailRequest) {
    return ApiResponse.ok(tenantService.detail(tenantDetailRequest));
  }

  /** 分页 */
  @PostMapping("/list")
  @ApiOperation(value = "分页", notes = "传入tenant")
  public ApiResponse<Page<TenantDetailResponse>> list(
      @Valid @RequestBody TenantPageRequest tenantPageRequest) {
    return ApiResponse.ok(tenantService.page(tenantPageRequest));
  }

  /** 下拉数据源 */
  @PostMapping("/select")
  @ApiOperation(value = "下拉数据源", notes = "传入tenant")
  public ApiResponse<List<TenantDetailResponse>> select(
      @Valid @RequestBody TenantListRequest tenantListRequest) {
    return ApiResponse.ok(tenantService.list(tenantListRequest));
  }

  /** 新增或修改 */
  @PostMapping("/submit")
  @ApiOperation(value = "新增或修改", notes = "传入tenant")
  public ApiResponse<Void> submit(
      @Valid @RequestBody TenantAddOrUpdateRequest tenantAddOrUpdateRequest) {
    tenantService.addOrUpdate(tenantAddOrUpdateRequest);
    return ApiResponse.ok();
  }

  /** 删除 */
  @PostMapping("/remove")
  @ApiOperation(value = "逻辑删除", notes = "传入ids")
  public ApiResponse<Void> remove(@RequestBody TenantRemoveRequest tenantRemoveRequest) {
    tenantService.remove(tenantRemoveRequest);
    return ApiResponse.ok();
  }
}
