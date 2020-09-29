package com.ruoyi.flowable.controller;

import com.ruoyi.common.annotation.Log;

import com.ruoyi.common.core.domain.Result;
import com.ruoyi.flowable.common.BaseFlowableController;
import org.flowable.job.api.Job;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
@RestController
@RequestMapping("/flowable/processDefinitionJob")
public class ProcessDefinitionJobController extends BaseFlowableController {

    @PreAuthorize("@ss.hasPermi('flowable:processDefinitionJob:list')")
    @GetMapping(value = "/list")
    public List<Job> list(@RequestParam String processDefinitionId) {
        return managementService.createTimerJobQuery().processDefinitionId(processDefinitionId).list();
    }

    @Log(title = "新增流程定义定时任务")
    @PreAuthorize("@ss.hasPermi('flowable:processDefinitionJob:delete')")
    @DeleteMapping(value = "/delete")
    @Transactional(rollbackFor = Exception.class)
    public Result deleteJob(@RequestParam String jobId) {
        managementService.deleteTimerJob(jobId);
        return Result.ok();
    }
}
