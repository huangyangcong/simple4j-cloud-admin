package com.ruoyi.flowable.controller;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.Result;
import com.ruoyi.flowable.common.BaseFlowableController;
import com.ruoyi.flowable.service.FlowableTaskService;
import com.ruoyi.flowable.vo.IdentityRequest;
import org.flowable.identitylink.api.history.HistoricIdentityLink;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
@RestController
@RequestMapping("/flowable/taskIdentityLink")
public class TaskIdentityLinkController extends BaseFlowableController {
    @Autowired
    protected FlowableTaskService flowableTaskService;

    @PreAuthorize("@ss.hasPermi('flowable:taskIdentityLink:list')")
    @GetMapping(value = "/list")
    public Result list(@RequestParam String taskId) {
        HistoricTaskInstance task = flowableTaskService.getHistoricTaskInstanceNotNull(taskId);
        List<HistoricIdentityLink> historicIdentityLinks = historyService.getHistoricIdentityLinksForTask(task.getId());
        return Result.ok(responseFactory.createTaskIdentityResponseList(historicIdentityLinks));
    }

    @Log(title = "新增任务授权")
    @PreAuthorize("@ss.hasPermi('flowable:taskIdentityLink:save')")
    @PostMapping(value = "/save")
    public Result save(@RequestBody IdentityRequest taskIdentityRequest) {
        flowableTaskService.saveTaskIdentityLink(taskIdentityRequest);
        return Result.ok();
    }

    @Log(title = "删除任务授权")
    @PreAuthorize("@ss.hasPermi('flowable:taskIdentityLink:delete')")
    @DeleteMapping(value = "/delete")
    public Result deleteIdentityLink(@RequestParam String taskId, @RequestParam String identityId,
                                     @RequestParam String identityType) {
        flowableTaskService.deleteTaskIdentityLink(taskId, identityId, identityType);
        return Result.ok();
    }
}
