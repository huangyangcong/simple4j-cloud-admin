package com.ruoyi.flowable.controller;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.Result;
import com.ruoyi.common.utils.ObjectUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flowable.common.BaseFlowableController;
import com.ruoyi.flowable.common.FlowablePage;
import com.ruoyi.flowable.constant.FlowableConstant;
import com.ruoyi.flowable.service.FlowableTaskService;
import com.ruoyi.flowable.vo.FlowNodeResponse;
import com.ruoyi.flowable.vo.TaskRequest;
import com.ruoyi.flowable.vo.TaskResponse;
import com.ruoyi.flowable.vo.TaskUpdateRequest;
import com.ruoyi.flowable.wapper.TaskListWrapper;
import com.ruoyi.flowable.wapper.TaskTodoListWrapper;
import org.flowable.common.engine.api.query.QueryProperty;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.flowable.task.service.impl.HistoricTaskInstanceQueryProperty;
import org.flowable.task.service.impl.TaskQueryProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kubilewang
 * @date 2020年3月23日
 */
@RestController
@RequestMapping("/flowable/task")
public class TaskController extends BaseFlowableController {
    @Autowired
    protected FlowableTaskService flowableTaskService;

    private static Map<String, QueryProperty> allowedSortProperties = new HashMap<>();
    private static Map<String, QueryProperty> allowedSortPropertiesTodo = new HashMap<>();

    static {
        allowedSortProperties.put("deleteReason", HistoricTaskInstanceQueryProperty.DELETE_REASON);
        allowedSortProperties.put("duration", HistoricTaskInstanceQueryProperty.DURATION);
        allowedSortProperties.put("endTime", HistoricTaskInstanceQueryProperty.END);
        allowedSortProperties.put(FlowableConstant.EXECUTION_ID, HistoricTaskInstanceQueryProperty.EXECUTION_ID);
        allowedSortProperties.put("taskInstanceId", HistoricTaskInstanceQueryProperty.HISTORIC_TASK_INSTANCE_ID);
        allowedSortProperties.put(FlowableConstant.PROCESS_DEFINITION_ID,
                HistoricTaskInstanceQueryProperty.PROCESS_DEFINITION_ID);
        allowedSortProperties.put(FlowableConstant.PROCESS_INSTANCE_ID,
                HistoricTaskInstanceQueryProperty.PROCESS_INSTANCE_ID);
        allowedSortProperties.put("assignee", HistoricTaskInstanceQueryProperty.TASK_ASSIGNEE);
        allowedSortProperties.put(FlowableConstant.TASK_DEFINITION_KEY,
                HistoricTaskInstanceQueryProperty.TASK_DEFINITION_KEY);
        allowedSortProperties.put("description", HistoricTaskInstanceQueryProperty.TASK_DESCRIPTION);
        allowedSortProperties.put("dueDate", HistoricTaskInstanceQueryProperty.TASK_DUE_DATE);
        allowedSortProperties.put(FlowableConstant.NAME, HistoricTaskInstanceQueryProperty.TASK_NAME);
        allowedSortProperties.put("owner", HistoricTaskInstanceQueryProperty.TASK_OWNER);
        allowedSortProperties.put("priority", HistoricTaskInstanceQueryProperty.TASK_PRIORITY);
        allowedSortProperties.put(FlowableConstant.TENANT_ID, HistoricTaskInstanceQueryProperty.TENANT_ID_);
        allowedSortProperties.put("startTime", HistoricTaskInstanceQueryProperty.START);

        allowedSortPropertiesTodo.put(FlowableConstant.PROCESS_DEFINITION_ID, TaskQueryProperty.PROCESS_DEFINITION_ID);
        allowedSortPropertiesTodo.put(FlowableConstant.PROCESS_INSTANCE_ID, TaskQueryProperty.PROCESS_INSTANCE_ID);
        allowedSortPropertiesTodo.put(FlowableConstant.TASK_DEFINITION_KEY, TaskQueryProperty.TASK_DEFINITION_KEY);
        allowedSortPropertiesTodo.put("dueDate", TaskQueryProperty.DUE_DATE);
        allowedSortPropertiesTodo.put(FlowableConstant.NAME, TaskQueryProperty.NAME);
        allowedSortPropertiesTodo.put("priority", TaskQueryProperty.PRIORITY);
        allowedSortPropertiesTodo.put(FlowableConstant.TENANT_ID, TaskQueryProperty.TENANT_ID);
        allowedSortPropertiesTodo.put("createTime", TaskQueryProperty.CREATE_TIME);
    }

    protected HistoricTaskInstanceQuery createHistoricTaskInstanceQuery(Map<String, String> requestParams) {
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_ID))) {
            query.taskId(requestParams.get(FlowableConstant.TASK_ID));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.PROCESS_INSTANCE_ID))) {
            query.processInstanceId(requestParams.get(FlowableConstant.PROCESS_INSTANCE_ID));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.PROCESS_INSTANCE_BUSINESS_KEY))) {
            query.processInstanceBusinessKeyLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.PROCESS_INSTANCE_BUSINESS_KEY)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.PROCESS_DEFINITION_KEY))) {
            query.processDefinitionKeyLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.PROCESS_DEFINITION_KEY)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.PROCESS_DEFINITION_ID))) {
            query.processDefinitionId(requestParams.get(FlowableConstant.PROCESS_DEFINITION_ID));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.PROCESS_DEFINITION_NAME))) {
            query.processDefinitionNameLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.PROCESS_DEFINITION_NAME)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.EXECUTION_ID))) {
            query.executionId(requestParams.get(FlowableConstant.EXECUTION_ID));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_NAME))) {
            query.taskNameLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.TASK_NAME)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_DESCRIPTION))) {
            query.taskDescriptionLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.TASK_DESCRIPTION)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_DEFINITION_KEY))) {
            query.taskDefinitionKeyLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.TASK_DEFINITION_KEY)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_ASSIGNEE))) {
            query.taskAssignee(requestParams.get(FlowableConstant.TASK_ASSIGNEE));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_OWNER))) {
            query.taskOwner(requestParams.get(FlowableConstant.TASK_OWNER));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_INVOLVED_USER))) {
            query.taskInvolvedUser(requestParams.get(FlowableConstant.TASK_INVOLVED_USER));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_PRIORITY))) {
            query.taskPriority(ObjectUtils.convertToInteger(requestParams.get(FlowableConstant.TASK_PRIORITY)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.FINISHED))) {
            boolean isFinished = ObjectUtils.convertToBoolean(requestParams.get(FlowableConstant.FINISHED));
            if (isFinished) {
                query.finished();
            } else {
                query.unfinished();
            }
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.PROCESS_FINISHED))) {
            boolean isProcessFinished =
                    ObjectUtils.convertToBoolean(requestParams.get(FlowableConstant.PROCESS_FINISHED));
            if (isProcessFinished) {
                query.processFinished();
            } else {
                query.processUnfinished();
            }
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.PARENT_TASK_ID))) {
            query.taskParentTaskId(requestParams.get(FlowableConstant.PARENT_TASK_ID));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TENANT_ID))) {
            query.taskTenantId(requestParams.get(FlowableConstant.TENANT_ID));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_CANDIDATE_USER))) {
            query.taskCandidateUser(requestParams.get(FlowableConstant.TASK_CANDIDATE_USER));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_CANDIDATE_GROUP))) {
            query.taskCandidateGroup(requestParams.get(FlowableConstant.TASK_CANDIDATE_GROUP));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_CANDIDATE_GROUPS))) {
            query.taskCandidateGroupIn(Arrays.asList(requestParams.get(FlowableConstant.TASK_CANDIDATE_GROUPS).split(
                    ",")));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.DUE_DATE_AFTER))) {
            query.taskDueAfter(ObjectUtils.convertToDate(requestParams.get(FlowableConstant.DUE_DATE_AFTER)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.DUE_DATE_BEFORE))) {
            query.taskDueBefore(ObjectUtils.convertToDate(requestParams.get(FlowableConstant.DUE_DATE_BEFORE)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_CREATED_BEFORE))) {
            query.taskCreatedBefore(ObjectUtils.convertToDatetime(requestParams.get(FlowableConstant.TASK_CREATED_BEFORE)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_CREATED_AFTER))) {
            query.taskCreatedAfter(ObjectUtils.convertToDatetime(requestParams.get(FlowableConstant.TASK_CREATED_AFTER)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_COMPLETED_BEFORE))) {
            query.taskCompletedBefore(ObjectUtils.convertToDatetime(requestParams.get(FlowableConstant.TASK_COMPLETED_BEFORE)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_COMPLETED_AFTER))) {
            query.taskCompletedAfter(ObjectUtils.convertToDatetime(requestParams.get(FlowableConstant.TASK_COMPLETED_AFTER)));
        }
        return query;
    }

    protected TaskQuery createTaskQuery(Map<String, String> requestParams) {
        TaskQuery query = taskService.createTaskQuery();
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.PROCESS_INSTANCE_ID))) {
            query.processInstanceId(requestParams.get(FlowableConstant.PROCESS_INSTANCE_ID));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_NAME))) {
            query.taskNameLike(requestParams.get(FlowableConstant.TASK_NAME));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.PROCESS_INSTANCE_BUSINESS_KEY))) {
            query.processInstanceBusinessKeyLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.PROCESS_INSTANCE_BUSINESS_KEY)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.PROCESS_DEFINITION_KEY))) {
            query.processDefinitionKeyLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.PROCESS_DEFINITION_KEY)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.PROCESS_DEFINITION_ID))) {
            query.processDefinitionId(requestParams.get(FlowableConstant.PROCESS_DEFINITION_ID));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.PROCESS_DEFINITION_NAME))) {
            query.processDefinitionNameLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.PROCESS_DEFINITION_NAME)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.DUE_DATE_AFTER))) {
            query.taskDueAfter(ObjectUtils.convertToDate(requestParams.get(FlowableConstant.DUE_DATE_AFTER)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.DUE_DATE_BEFORE))) {
            query.taskDueBefore(ObjectUtils.convertToDate(requestParams.get(FlowableConstant.DUE_DATE_BEFORE)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_CREATED_BEFORE))) {
            query.taskCreatedBefore(ObjectUtils.convertToDatetime(requestParams.get(FlowableConstant.TASK_CREATED_BEFORE)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TASK_CREATED_AFTER))) {
            query.taskCreatedAfter(ObjectUtils.convertToDatetime(requestParams.get(FlowableConstant.TASK_CREATED_AFTER)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TENANT_ID))) {
            query.taskTenantId(requestParams.get(FlowableConstant.TENANT_ID));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.SUSPENDED))) {
            boolean isSuspended = ObjectUtils.convertToBoolean(requestParams.get(FlowableConstant.SUSPENDED));
            if (isSuspended) {
                query.suspended();
            } else {
                query.active();
            }
        }
        return query;
    }

    @PreAuthorize("@ss.hasPermi('flowable:task:list')")
    @GetMapping(value = "/list")
    public Result list(@RequestParam Map<String, String> requestParams) {
        HistoricTaskInstanceQuery query = createHistoricTaskInstanceQuery(requestParams);
        FlowablePage page = this.pageList(requestParams, query, TaskListWrapper.class, allowedSortProperties,
                HistoricTaskInstanceQueryProperty.START);
        return Result.ok(page);
    }

    @GetMapping(value = "/listDone")
    public Result listDone(@RequestParam Map<String, String> requestParams) {
        HistoricTaskInstanceQuery query = createHistoricTaskInstanceQuery(requestParams);
        query.finished().or().taskAssignee(SecurityUtils.getUserId()).taskOwner(SecurityUtils.getUserId()).endOr();
        FlowablePage page = this.pageList(requestParams, query, TaskListWrapper.class, allowedSortProperties,
                HistoricTaskInstanceQueryProperty.START);
        return Result.ok(page);
    }

    @GetMapping(value = "/listTodo")
    public Result listTodo(@RequestParam Map<String, String> requestParams) {
        String userId = SecurityUtils.getUserId();
        TaskQuery query = createTaskQuery(requestParams);
        query.or().taskCandidateOrAssigned(userId).taskOwner(userId).endOr();
        FlowablePage page = this.pageList(requestParams, query, TaskTodoListWrapper.class, allowedSortProperties,
                TaskQueryProperty.CREATE_TIME);
        return Result.ok(page);
    }

    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String taskId) {
        TaskResponse task = flowableTaskService.getTask(taskId);
        return Result.ok(task);
    }

    @Log(title = "修改任务")
    @PreAuthorize("@ss.hasPermi('flowable:task:update')")
    @PutMapping(value = "/update")
    public Result update(@RequestBody TaskUpdateRequest taskUpdateRequest) {
        TaskResponse task = flowableTaskService.updateTask(taskUpdateRequest);
        return Result.ok(task);
    }

    @Log(title = "删除任务")
    @PreAuthorize("@ss.hasPermi('flowable:task:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String taskId) {
        flowableTaskService.deleteTask(taskId);
        return Result.ok();
    }

    @Log(title = "转办任务")
    @PutMapping(value = "/assign")
    public Result assign(@RequestBody TaskRequest taskRequest) {
        flowableTaskService.assignTask(taskRequest);
        return Result.ok();
    }

    @Log(title = "委派任务")
    @PutMapping(value = "/delegate")
    public Result delegate(@RequestBody TaskRequest taskRequest) {
        flowableTaskService.delegateTask(taskRequest);
        return Result.ok();
    }

    @Log(title = "认领任务")
    @PutMapping(value = "/claim")
    public Result claim(@RequestBody TaskRequest taskRequest) {
        flowableTaskService.claimTask(taskRequest);
        return Result.ok();
    }

    @Log(title = "取消认领任务")
    @PutMapping(value = "/unclaim")
    public Result unclaim(@RequestBody TaskRequest taskRequest) {
        flowableTaskService.unclaimTask(taskRequest);
        return Result.ok();
    }

    @Log(title = "完成任务")
    @PutMapping(value = "/complete")
    public Result complete(@RequestBody TaskRequest taskRequest) {
        flowableTaskService.completeTask(taskRequest);
        return Result.ok();
    }

    @Log(title = "结束流程实例")
    @PutMapping(value = "/stopProcessInstance")
    public Result stopProcessInstance(@RequestBody TaskRequest taskRequest) {
        flowableTaskService.stopProcessInstance(taskRequest);
        return Result.ok();
    }

    @GetMapping(value = "/renderedTaskForm")
    public Result renderedTaskForm(@RequestParam String taskId) {
        permissionService.validateReadPermissionOnTask2(taskId, SecurityUtils.getUserId(), true, true);
        Object renderedTaskForm = formService.getRenderedTaskForm(taskId);
        return Result.ok(renderedTaskForm);
    }

    @GetMapping(value = "/executeTaskData")
    public Result executeTaskData(@RequestParam String taskId) {
        Task task = permissionService.validateReadPermissionOnTask2(taskId, SecurityUtils.getUserId(), true, true);
        String startFormKey = formService.getStartFormKey(task.getProcessDefinitionId());
        String taskFormKey = formService.getTaskFormKey(task.getProcessDefinitionId(), task.getTaskDefinitionKey());
        Object renderedStartForm = formService.getRenderedStartForm(task.getProcessDefinitionId());
        Object renderedTaskForm = formService.getRenderedTaskForm(taskId);
        Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());

        ProcessInstance processInstance =
                runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        Map<String, Object> ret = new HashMap<String, Object>(7);
        ret.put("startUserId", processInstance.getStartUserId());
        ret.put("startFormKey", startFormKey);
        ret.put("taskFormKey", taskFormKey);
        ret.put("renderedStartForm", renderedStartForm);
        ret.put("renderedTaskForm", renderedTaskForm);
        ret.put("variables", variables);
        boolean showBusinessKey = isShowBusinessKey(task.getProcessDefinitionId());
        ret.put("showBusinessKey", showBusinessKey);
        ret.put(FlowableConstant.BUSINESS_KEY, processInstance.getBusinessKey());
        // 当前任务是发起者
        if (FlowableConstant.INITIATOR.equals(task.getTaskDefinitionKey())) {
            ret.put("isInitiator", true);
        }
        return Result.ok(ret);
    }

    @GetMapping(value = "/backNodes")
    public Result backNodes(@RequestParam String taskId) {
        List<FlowNodeResponse> datas = flowableTaskService.getBackNodes(taskId);
        return Result.ok(datas);
    }

    @Log(title = "退户任务")
    @PutMapping(value = "/back")
    public Result back(@RequestBody TaskRequest taskRequest) {
        flowableTaskService.backTask(taskRequest);
        return Result.ok();
    }
}
