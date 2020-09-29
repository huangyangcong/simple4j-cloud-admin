package com.ruoyi.flowable.controller;

import com.ruoyi.common.annotation.Log;

import com.ruoyi.common.core.domain.Result;
import com.ruoyi.flowable.common.BaseFlowableController;
import com.ruoyi.flowable.service.ProcessDefinitionService;
import com.ruoyi.flowable.vo.IdentityRequest;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.identitylink.api.IdentityLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
@RestController
@RequestMapping("/flowable/processDefinitionIdentityLink")
public class ProcessDefinitionIdentityLinkController extends BaseFlowableController {
    @Autowired
    private ProcessDefinitionService processDefinitionService;

    @PreAuthorize("@ss.hasPermi('flowable:processDefinitionIdentityLink:list')")
    @GetMapping(value = "/list")
    public Result list(@RequestParam String processDefinitionId) {
        ProcessDefinition processDefinition = processDefinitionService.getProcessDefinitionById(processDefinitionId);
        List<IdentityLink> identityLinks =
                repositoryService.getIdentityLinksForProcessDefinition(processDefinition.getId());
        return Result.ok(responseFactory.createIdentityResponseList(identityLinks));
    }

    @Log(title = "新增流程定义授权")
    @PreAuthorize("@ss.hasPermi('flowable:processDefinitionIdentityLink:save')")
    @PostMapping(value = "/save")
    public Result save(@RequestBody IdentityRequest identityRequest) {
        processDefinitionService.saveProcessDefinitionIdentityLink(identityRequest);
        return Result.ok();
    }

    @Log(title = "删除流程定义授权")
    @PreAuthorize("@ss.hasPermi('flowable:processDefinitionIdentityLink:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String processDefinitionId, @RequestParam String identityId,
                         @RequestParam String identityType) {
        processDefinitionService.deleteProcessDefinitionIdentityLink(processDefinitionId, identityId, identityType);
        return Result.ok();
    }
}
