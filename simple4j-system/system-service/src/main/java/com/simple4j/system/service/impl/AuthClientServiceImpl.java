package com.simple4j.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.api.base.Page;
import com.simple4j.system.entity.AuthClient;
import com.simple4j.system.mapper.AuthClientMapper;
import com.simple4j.system.mapstruct.ClientMapStruct;
import com.simple4j.system.request.ClientAddOrUpdateRequest;
import com.simple4j.system.request.ClientAddRequest;
import com.simple4j.system.request.ClientDetailRequest;
import com.simple4j.system.request.ClientListRequest;
import com.simple4j.system.request.ClientPageRequest;
import com.simple4j.system.request.ClientRemoveRequest;
import com.simple4j.system.request.ClientUpdateRequest;
import com.simple4j.system.response.ClientDetailResponse;
import com.simple4j.system.service.IAuthClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 *
 * @author hyc
 */
@Service
@RequiredArgsConstructor
public class AuthClientServiceImpl implements IAuthClientService {

  private final ClientMapStruct clientMapStruct;
  private final AuthClientMapper authClientMapper;

  @Override
  public ClientDetailResponse detail(ClientDetailRequest clientDetailRequest) {
    AuthClient detail =
        authClientMapper.getOne(
            Wrappers.<AuthClient>lambdaQuery().eq(AuthClient::getId, clientDetailRequest.getId()));
    return clientMapStruct.toVo(detail);
  }

  @Override
  public List<ClientDetailResponse> list(ClientListRequest clientListRequest) {
    LambdaQueryWrapper<AuthClient> queryWrapper = Wrappers.lambdaQuery();
    List<AuthClient> pages = authClientMapper.list(queryWrapper);
    return clientMapStruct.toVo(pages);
  }

  @Override
  public Page<ClientDetailResponse> page(ClientPageRequest clientPageRequest) {
    IPage<AuthClient> page =
        authClientMapper.page(
            new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
                clientPageRequest.getPageNo(), clientPageRequest.getPageSize()),
            Wrappers.<AuthClient>lambdaQuery()
                .eq(
                    StrUtil.isNotEmpty(clientPageRequest.getClientId()),
                    AuthClient::getClientId,
                    clientPageRequest.getClientId())
                .eq(
                    StrUtil.isNotEmpty(clientPageRequest.getClientSecret()),
                    AuthClient::getClientSecret,
                    clientPageRequest.getClientSecret()));
    Page<AuthClient> pages =
        new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    return clientMapStruct.toVo(pages);
  }

  @Override
  public boolean add(ClientAddRequest clientAddRequest) {
    return authClientMapper.save(clientMapStruct.toPo(clientAddRequest));
  }

  @Override
  public boolean update(ClientUpdateRequest clientUpdateRequest) {
    return authClientMapper.updateByIdBool(clientMapStruct.toPo(clientUpdateRequest));
  }

  @Override
  public boolean addOrUpdate(ClientAddOrUpdateRequest clientAddOrUpdateRequest) {
    return authClientMapper.saveOrUpdate(clientMapStruct.toPo(clientAddOrUpdateRequest));
  }

  @Override
  public boolean remove(ClientRemoveRequest clientRemoveRequest) {
    return authClientMapper.removeByIds(clientRemoveRequest.getIds());
  }
}
