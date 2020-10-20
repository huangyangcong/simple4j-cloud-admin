package com.simple4j.flow.service.impl;

import cn.hutool.core.util.StrUtil;
import com.simple4j.api.base.BusinessException;
import com.simple4j.autoconfigure.jwt.security.SecurityUtils;
import com.simple4j.flow.cmd.AddCcIdentityLinkCmd;
import com.simple4j.flow.cmd.BackUserTaskCmd;
import com.simple4j.flow.cmd.CompleteTaskReadCmd;
import com.simple4j.flow.constant.FlowableConstant;
import com.simple4j.flow.enums.CommentTypeEnum;
import com.simple4j.flow.request.AddCommentRequest;
import com.simple4j.flow.request.IdentityRequest;
import com.simple4j.flow.request.TaskRequest;
import com.simple4j.flow.request.TaskUpdateRequest;
import com.simple4j.flow.response.FlowNodeResponse;
import com.simple4j.flow.response.TaskResponse;
import com.simple4j.flow.service.FlowableTaskService;
import com.simple4j.flow.util.FlowableUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.constants.BpmnXMLConstants;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.*;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.FlowableIllegalArgumentException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.editor.language.json.converter.util.CollectionUtils;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ActivityInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.identitylink.api.IdentityLink;
import org.flowable.identitylink.api.IdentityLinkType;
import org.flowable.identitylink.api.history.HistoricIdentityLink;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskInfo;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 庄金明
 * @date 2020年3月23日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FlowableTaskServiceImpl implements FlowableTaskService {
    @Autowired
    protected IdentityService identityService;
    @Autowired
    protected RepositoryService repositoryService;
    @Autowired
    protected TaskService taskService;
    @Autowired
    protected RuntimeService runtimeService;
    @Autowired
    protected HistoryService historyService;
    @Autowired
    protected PermissionServiceImpl permissionService;
    @Autowired
    protected ManagementService managementService;
    @Autowired
    protected FormService formService;

    @Override
    public TaskResponse getTask(String taskId) {
        String userId = SecurityUtils.getCurrentUserId();
        HistoricTaskInstance taskHis = permissionService.validateReadPermissionOnTask(taskId, userId, true, true);
        TaskResponse rep = null;
        ProcessDefinition processDefinition = null;
        String formKey = null;
        Object renderedTaskForm = null;
        HistoricTaskInstance parentTask = null;
        if (StringUtils.isNotEmpty(taskHis.getProcessDefinitionId())) {
            processDefinition = repositoryService.getProcessDefinition(taskHis.getProcessDefinitionId());
            formKey = formService.getTaskFormKey(processDefinition.getId(), taskHis.getTaskDefinitionKey());
            if (taskHis.getEndTime() == null && formKey != null && formKey.length() > 0) {
                renderedTaskForm = formService.getRenderedTaskForm(taskId);
            }
        }
        if (StringUtils.isNotEmpty(taskHis.getParentTaskId())) {
            parentTask =
                    historyService.createHistoricTaskInstanceQuery().taskId(taskHis.getParentTaskId()).singleResult();
        }
        rep = new TaskResponse(taskHis, processDefinition, parentTask, null);
        rep.setFormKey(formKey);
        rep.setRenderedTaskForm(renderedTaskForm);

        fillPermissionInformation(rep, taskHis, userId);
        // Populate the people
        populateAssignee(taskHis, rep);
        rep.setInvolvedPeople(getInvolvedUsers(taskId));

        Task task = null;
        if (taskHis.getEndTime() == null) {
            task = taskService.createTaskQuery().taskId(taskId).singleResult();
            rep.setSuspended(task.isSuspended());
            rep.setDelegationState(task.getDelegationState());
        }
        rep.setOwnerName(this.getUserName(taskHis.getOwner()));
        rep.setAssigneeName(this.getUserName(taskHis.getAssignee()));
        return rep;
    }

    @Override
    public List<TaskResponse> getSubTasks(String taskId) {
		String userId = SecurityUtils.getCurrentUserId();
        HistoricTaskInstance parentTask = permissionService.validateReadPermissionOnTask(taskId, userId, true, true);
        List<Task> subTasks = this.taskService.getSubTasks(taskId);
        List<TaskResponse> subTasksRepresentations = new ArrayList<>(subTasks.size());
        for (Task subTask : subTasks) {
            TaskResponse representation = new TaskResponse(subTask, parentTask);
            fillPermissionInformation(representation, subTask, userId);
            populateAssignee(subTask, representation);
            representation.setInvolvedPeople(getInvolvedUsers(subTask.getId()));
            subTasksRepresentations.add(representation);
        }
        return subTasksRepresentations;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TaskResponse updateTask(TaskUpdateRequest taskUpdateRequest) {
		String userId = SecurityUtils.getCurrentUserId();
        permissionService.validateReadPermissionOnTask(taskUpdateRequest.getId(), userId, false, false);
        Task task = getTaskNotNull(taskUpdateRequest.getId());
        task.setName(taskUpdateRequest.getName());
        task.setDescription(taskUpdateRequest.getDescription());
        task.setAssignee(taskUpdateRequest.getAssignee());
        task.setOwner(taskUpdateRequest.getOwner());
        task.setDueDate(taskUpdateRequest.getDueDate());
        task.setPriority(taskUpdateRequest.getPriority());
        task.setCategory(taskUpdateRequest.getCategory());
        taskService.saveTask(task);
        return new TaskResponse(task);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignTask(TaskRequest taskRequest) {
        String taskId = taskRequest.getTaskId();
        String assignee = taskRequest.getUserId();
		String userId = SecurityUtils.getCurrentUserId();
        Task task = permissionService.validateAssignPermissionOnTask(taskId, userId, assignee);
		AddCommentRequest addCommentRequest = new AddCommentRequest();
		addCommentRequest.setTaskId(taskId);
		addCommentRequest.setProcessInstanceId(task.getProcessInstanceId());
		addCommentRequest.setUserId(userId);
		addCommentRequest.setType(CommentTypeEnum.ZB.name());
		addCommentRequest.setMessage(taskRequest.getMessage());
        this.addComment(addCommentRequest);
        taskService.setAssignee(task.getId(), assignee);
        // 暂时转办人员不作为参与者
        // String oldAssignee = task.getAssignee();
        // // If the old assignee user wasn't part of the involved users yet, make it so
        // addIdentiyLinkForUser(task, oldAssignee, IdentityLinkType.PARTICIPANT);
        // // If the current user wasn't part of the involved users yet, make it so
        // addIdentiyLinkForUser(task, userId, IdentityLinkType.PARTICIPANT);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void involveUser(String taskId, String involveUserId) {
        Task task = getTaskNotNull(taskId);
		String userId = SecurityUtils.getCurrentUserId();
        permissionService.validateReadPermissionOnTask(task.getId(), userId, false, false);
        if (involveUserId != null && involveUserId.length() > 0) {
            taskService.addUserIdentityLink(taskId, involveUserId, IdentityLinkType.PARTICIPANT);
        } else {
            throw new FlowableException("User id is required");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeInvolvedUser(String taskId, String involveUserId) {
        Task task = getTaskNotNull(taskId);
		String userId = SecurityUtils.getCurrentUserId();
        permissionService.validateReadPermissionOnTask(task.getId(), userId, false, false);
        if (involveUserId != null && involveUserId.length() > 0) {
            taskService.deleteUserIdentityLink(taskId, involveUserId, IdentityLinkType.PARTICIPANT);
        } else {
            throw new FlowableException("User id is required");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void claimTask(TaskRequest taskRequest) {
        String taskId = taskRequest.getTaskId();
		String userId = SecurityUtils.getCurrentUserId();
        TaskInfo task = permissionService.validateReadPermissionOnTask2(taskId, userId, false, false);
        if (task.getAssignee() != null && task.getAssignee().length() > 0) {
            throw new BusinessException("User does not have permission");
        }
		AddCommentRequest addCommentRequest = new AddCommentRequest();
		addCommentRequest.setTaskId(taskId);
		addCommentRequest.setProcessInstanceId(task.getProcessInstanceId());
		addCommentRequest.setUserId(userId);
		addCommentRequest.setType(CommentTypeEnum.RL.name());
		addCommentRequest.setMessage(taskRequest.getMessage());
		this.addComment(addCommentRequest);
        taskService.claim(taskId, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unclaimTask(TaskRequest taskRequest) {
        String taskId = taskRequest.getTaskId();
		String userId = SecurityUtils.getCurrentUserId();
        TaskInfo task = this.getTaskNotNull(taskId);
        if (!userId.equals(task.getAssignee())) {
            throw new BusinessException("User does not have permission");
        }
        if (FlowableConstant.CATEGORY_TO_READ.equals(task.getCategory())) {
            throw new BusinessException("User cannot unclaim the read task");
        }
        if (FlowableConstant.INITIATOR.equals(task.getTaskDefinitionKey())) {
            throw new BusinessException("Initiator cannot unclaim the task");
        }
		AddCommentRequest addCommentRequest = new AddCommentRequest();
		addCommentRequest.setTaskId(taskId);
		addCommentRequest.setProcessInstanceId(task.getProcessInstanceId());
		addCommentRequest.setUserId(userId);
		addCommentRequest.setType(CommentTypeEnum.QXRL.name());
		addCommentRequest.setMessage(taskRequest.getMessage());
        this.addComment(addCommentRequest);
        taskService.unclaim(taskId);
        // 判断是否是协办取消认领
        if (permissionService.isTaskPending((Task) task)) {
            taskService.resolveTask(taskId, null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addIdentiyLinkForUser(Task task, String userId, String linkType) {
        List<IdentityLink> identityLinks = taskService.getIdentityLinksForTask(task.getId());
        boolean isOldUserInvolved = false;
        for (IdentityLink identityLink : identityLinks) {
            isOldUserInvolved =
                    userId.equals(identityLink.getUserId()) && (identityLink.getType().equals(IdentityLinkType.PARTICIPANT) || identityLink.getType().equals(IdentityLinkType.CANDIDATE));
            if (isOldUserInvolved) {
                break;
            }
        }
        if (!isOldUserInvolved) {
            taskService.addUserIdentityLink(task.getId(), userId, linkType);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delegateTask(TaskRequest taskRequest) {
        String taskId = taskRequest.getTaskId();
        String delegater = taskRequest.getUserId();
		String userId = SecurityUtils.getCurrentUserId();
        Task task = permissionService.validateDelegatePermissionOnTask(taskId, userId, delegater);
		AddCommentRequest addCommentRequest = new AddCommentRequest();
		addCommentRequest.setTaskId(taskId);
		addCommentRequest.setProcessInstanceId(task.getProcessInstanceId());
		addCommentRequest.setUserId(userId);
		addCommentRequest.setType(CommentTypeEnum.WP.name());
		addCommentRequest.setMessage(taskRequest.getMessage());
        this.addComment(addCommentRequest);
        taskService.delegateTask(task.getId(), delegater);
        // 暂时委派人员不作为参与者
        // String oldAssignee = task.getAssignee();
        // // If the old assignee user wasn't part of the involved users yet, make it so
        // addIdentiyLinkForUser(task, oldAssignee, IdentityLinkType.PARTICIPANT);
        // // If the current user wasn't part of the involved users yet, make it so
        // addIdentiyLinkForUser(task, userId, IdentityLinkType.PARTICIPANT);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void completeTask(TaskRequest taskRequest) {
        String taskId = taskRequest.getTaskId();
		String userId = SecurityUtils.getCurrentUserId();
        Task task = getTaskNotNull(taskId);
        if (!permissionService.isTaskOwnerOrAssignee(userId, task)) {
            if (StringUtils.isEmpty(task.getScopeType()) && !permissionService.validateIfUserIsInitiatorAndCanCompleteTask(userId, task)) {
                throw new BusinessException("User does not have permission");
            }
        }

        Map<String, Object> completeVariables = null;
        if (taskRequest.getValues() != null && !taskRequest.getValues().isEmpty()) {
            completeVariables = taskRequest.getValues();
            // 允许任务表单修改流程表单场景 begin
            // 与前端约定：流程表单变量名为 processInstanceFormData，且只有流程表单startFormKey=taskFormKey时才允许修改该变量的值，防止恶意节点修改流程表单内容
            if (completeVariables.containsKey(FlowableConstant.PROCESS_INSTANCE_FORM_DATA)) {
                String startFormKey = formService.getStartFormKey(task.getProcessDefinitionId());
                String taskFormKey = formService.getTaskFormKey(task.getProcessDefinitionId(),
                        task.getTaskDefinitionKey());
                boolean modifyProcessInstanceFormData =
                        StrUtil.isNotEmpty(startFormKey) && StrUtil.isNotEmpty(taskFormKey) && startFormKey.equals(taskFormKey);
                if (!modifyProcessInstanceFormData) {
                    throw new BusinessException("User does not have permission");
                }
            }
            // 允许任务表单修改流程表单场景 end

        }
		AddCommentRequest addCommentRequest = new AddCommentRequest();
		addCommentRequest.setTaskId(taskId);
		addCommentRequest.setProcessInstanceId(task.getProcessInstanceId());
		addCommentRequest.setUserId(userId);
		addCommentRequest.setType( FlowableConstant.INITIATOR.equals(task.getTaskDefinitionKey()) ?
				CommentTypeEnum.CXTJ.name() :
				CommentTypeEnum.WC.name());
		addCommentRequest.setMessage(taskRequest.getMessage());
        this.addComment(addCommentRequest);

        // 处理抄送
        if (!ObjectUtils.isEmpty(taskRequest.getCcToVos())) {
            managementService.executeCommand(new AddCcIdentityLinkCmd(task.getProcessInstanceId(), task.getId(),
                    userId, taskRequest.getCcToVos()));
        }

        if (task.getAssignee() == null || !task.getAssignee().equals(userId)) {
            taskService.setAssignee(taskId, userId);
        }
        // 判断是否是协办完成还是正常流转
        if (permissionService.isTaskPending(task)) {
            taskService.resolveTask(taskId, completeVariables);
            // 如果当前执行人是任务所有人，直接完成任务
            if (userId.equals(task.getOwner())) {
                taskService.complete(taskId, completeVariables);
            }
        } else {
            taskService.complete(taskId, completeVariables);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTask(String taskId) {
        HistoricTaskInstance task = this.getHistoricTaskInstanceNotNull(taskId);
        if (task.getEndTime() == null) {
            throw new FlowableException("Cannot delete task that is running");
        }
        historyService.deleteHistoricTaskInstance(task.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopProcessInstance(TaskRequest taskRequest) {
        String taskId = taskRequest.getTaskId();
		String userId = SecurityUtils.getCurrentUserId();
        ProcessInstance processInstance = permissionService.validateStopProcessInstancePermissionOnTask(taskId, userId);
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        if (bpmnModel != null) {
            Process process = bpmnModel.getMainProcess();
            List<EndEvent> endNodes = process.findFlowElementsOfType(EndEvent.class, false);
            if (endNodes != null && endNodes.size() > 0) {
				AddCommentRequest addCommentRequest = new AddCommentRequest();
				addCommentRequest.setTaskId(taskId);
				addCommentRequest.setProcessInstanceId(processInstance.getProcessInstanceId());
				addCommentRequest.setUserId(userId);
				addCommentRequest.setType(CommentTypeEnum.ZZ.name());
				addCommentRequest.setMessage(taskRequest.getMessage());
                this.addComment(addCommentRequest);
                String endId = endNodes.get(0).getId();
                List<Execution> executions =
                        runtimeService.createExecutionQuery().parentId(processInstance.getProcessInstanceId()).list();
                List<String> executionIds = new ArrayList<>();
                executions.forEach(execution -> executionIds.add(execution.getId()));
                runtimeService.createChangeActivityStateBuilder().moveExecutionsToSingleActivityId(executionIds,
                        endId).changeState();
            }
        }
    }

    @Override
    public List<FlowNodeResponse> getBackNodes(String taskId) {
		String userId = SecurityUtils.getCurrentUserId();

		TaskEntity taskEntity = (TaskEntity) permissionService.validateExcutePermissionOnTask(taskId, userId);
        String processInstanceId = taskEntity.getProcessInstanceId();
        String currActId = taskEntity.getTaskDefinitionKey();
        String processDefinitionId = taskEntity.getProcessDefinitionId();
        Process process = repositoryService.getBpmnModel(processDefinitionId).getMainProcess();
        FlowNode currentFlowElement = (FlowNode) process.getFlowElement(currActId, true);
        List<ActivityInstance> activitys =
                runtimeService.createActivityInstanceQuery().processInstanceId(processInstanceId).finished().orderByActivityInstanceStartTime().asc().list();
        List<String> activityIds =
                activitys.stream().filter(activity -> activity.getActivityType().equals(BpmnXMLConstants.ELEMENT_TASK_USER)).filter(activity -> !activity.getActivityId().equals(currActId)).map(ActivityInstance::getActivityId).distinct().collect(Collectors.toList());
        List<FlowNodeResponse> result = new ArrayList<>();
        for (String activityId : activityIds) {
            FlowNode toBackFlowElement = (FlowNode) process.getFlowElement(activityId, true);
            if (FlowableUtils.isReachable(process, toBackFlowElement, currentFlowElement)) {
                FlowNodeResponse vo = new FlowNodeResponse();
                vo.setNodeId(activityId);
                vo.setNodeName(toBackFlowElement.getName());
                result.add(vo);
            }
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void backTask(TaskRequest taskRequest) {
        String taskId = taskRequest.getTaskId();
		String userId = SecurityUtils.getCurrentUserId();
        Task task = permissionService.validateExcutePermissionOnTask(taskId, userId);
        String backSysMessage = "退回到" + taskRequest.getActivityName() + "。";
		AddCommentRequest addCommentRequest = new AddCommentRequest();
		addCommentRequest.setTaskId(taskId);
		addCommentRequest.setProcessInstanceId(task.getProcessInstanceId());
		addCommentRequest.setUserId(userId);
		addCommentRequest.setType(CommentTypeEnum.TH.name());
		addCommentRequest.setMessage(backSysMessage + taskRequest.getMessage());
        this.addComment(addCommentRequest);
        String targetRealActivityId = managementService.executeCommand(new BackUserTaskCmd(runtimeService,
                taskRequest.getTaskId(), taskRequest.getActivityId()));
        // 退回发起者处理,退回到发起者,默认设置任务执行人为发起者
        if (FlowableConstant.INITIATOR.equals(targetRealActivityId)) {
            String initiator =
                    runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getStartUserId();
            List<Task> newTasks = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
            for (Task newTask : newTasks) {
                // 约定：发起者节点为 __initiator__
                if (FlowableConstant.INITIATOR.equals(newTask.getTaskDefinitionKey())) {
                    if (ObjectUtils.isEmpty(newTask.getAssignee())) {
                        taskService.setAssignee(newTask.getId(), initiator);
                    }
                }
            }
        }
    }

    private void fillPermissionInformation(TaskResponse taskResponse, TaskInfo task, String userId) {
        verifyProcessInstanceStartUser(taskResponse, task);
        List<HistoricIdentityLink> taskIdentityLinks = historyService.getHistoricIdentityLinksForTask(task.getId());
        verifyCandidateGroups(taskResponse, userId, taskIdentityLinks);
        verifyCandidateUsers(taskResponse, userId, taskIdentityLinks);
    }

    private void verifyProcessInstanceStartUser(TaskResponse taskResponse, TaskInfo task) {
        if (task.getProcessInstanceId() != null) {
            HistoricProcessInstance historicProcessInstance =
                    historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            if (historicProcessInstance != null && StringUtils.isNotEmpty(historicProcessInstance.getStartUserId())) {
                taskResponse.setProcessInstanceStartUserId(historicProcessInstance.getStartUserId());
                BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
                FlowElement flowElement = bpmnModel.getFlowElement(task.getTaskDefinitionKey());
                if (flowElement instanceof UserTask) {
                    UserTask userTask = (UserTask) flowElement;
                    List<ExtensionElement> extensionElements = userTask.getExtensionElements().get("initiator-can" +
                            "-complete");
                    if (CollectionUtils.isNotEmpty(extensionElements)) {
                        String value = extensionElements.get(0).getElementText();
                        if (StringUtils.isNotEmpty(value)) {
                            taskResponse.setInitiatorCanCompleteTask(value);
                        }
                    }
                }
            }
        }
    }

    private void verifyCandidateGroups(TaskResponse taskResponse, String userId,
                                       List<HistoricIdentityLink> taskIdentityLinks) {
        List<Group> userGroups = identityService.createGroupQuery().groupMember(userId).list();
        taskResponse.setMemberOfCandidateGroup(String.valueOf(userGroupsMatchTaskCandidateGroups(userGroups,
                taskIdentityLinks)));
    }

    private boolean userGroupsMatchTaskCandidateGroups(List<Group> userGroups,
                                                       List<HistoricIdentityLink> taskIdentityLinks) {
        for (Group group : userGroups) {
            for (HistoricIdentityLink identityLink : taskIdentityLinks) {
                if (identityLink.getGroupId() != null && identityLink.getType().equals(IdentityLinkType.CANDIDATE) && group.getId().equals(identityLink.getGroupId())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void verifyCandidateUsers(TaskResponse taskResponse, String userId,
                                      List<HistoricIdentityLink> taskIdentityLinks) {
        taskResponse.setMemberOfCandidateUsers(String.valueOf(currentUserMatchesTaskCandidateUsers(userId,
                taskIdentityLinks)));
    }

    private boolean currentUserMatchesTaskCandidateUsers(String userId, List<HistoricIdentityLink> taskIdentityLinks) {
        for (HistoricIdentityLink identityLink : taskIdentityLinks) {
            if (identityLink.getUserId() != null && identityLink.getType().equals(IdentityLinkType.CANDIDATE) && identityLink.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    private String getUserName(String userId) {
        if (StrUtil.isEmpty(userId)) {
            return null;
        }
        User user = identityService.createUserQuery().userId(userId).singleResult();
        if (user != null) {
            return user.getFirstName();
        }
        return null;
    }

    private List<String> getInvolvedUsers(String taskId) {
        List<HistoricIdentityLink> idLinks = historyService.getHistoricIdentityLinksForTask(taskId);
        List<String> result = new ArrayList<>(idLinks.size());

        for (HistoricIdentityLink link : idLinks) {
            // Only include users and non-assignee links
            if (link.getUserId() != null && !IdentityLinkType.ASSIGNEE.equals(link.getType())) {
                result.add(link.getUserId());
            }
        }
        return result;
    }

    private void populateAssignee(TaskInfo task, TaskResponse rep) {
        if (task.getAssignee() != null) {
            rep.setAssignee(task.getAssignee());
        }
    }

    @Override
    public Task getTaskNotNull(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new FlowableObjectNotFoundException("Task with id: " + taskId + " does not exist");
        }
        return task;
    }

    @Override
    public HistoricTaskInstance getHistoricTaskInstanceNotNull(String taskId) {
        HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new FlowableObjectNotFoundException("Task with id: " + taskId + " does not exist");
        }
        return task;
    }

    @Override
    public void addComment(AddCommentRequest addCommentRequest) {
		String userId = addCommentRequest.getUserId();
		String message = addCommentRequest.getMessage();
		String processInstanceId = addCommentRequest.getProcessInstanceId();
		String taskId = addCommentRequest.getTaskId();
		CommentTypeEnum type = CommentTypeEnum.getEnumByType(addCommentRequest.getType());
		Authentication.setAuthenticatedUserId(userId);
        type = type == null ? CommentTypeEnum.SP : type;
        message = (message == null || message.length() == 0) ? type.getName() : message;
        taskService.addComment(taskId, processInstanceId, type.toString(), message);
    }

    @Override
    public List<Comment> getComments(String taskId, String processInstanceId, String type, String userId) {
        List<Comment> comments = null;
        if (type == null || type.length() == 0) {
            // 以taskId为优先
            if (taskId != null && taskId.length() > 0) {
                comments = taskService.getTaskComments(taskId);
            } else if (processInstanceId != null && processInstanceId.length() > 0) {
                comments = taskService.getProcessInstanceComments(processInstanceId);
            } else {
                throw new FlowableIllegalArgumentException("taskId processInstanceId type are all empty");
            }
        } else {
            // 以taskId为优先
            if (taskId != null && taskId.length() > 0) {
                comments = taskService.getTaskComments(taskId, type);
            } else if (processInstanceId != null && processInstanceId.length() > 0) {
                comments = taskService.getProcessInstanceComments(processInstanceId, type);
            } else {
                comments = taskService.getCommentsByType(type);
            }
        }
        if (userId != null && userId.length() > 0 && comments != null && comments.size() > 0) {
            comments =
                    comments.stream().filter(comment -> userId.equals(comment.getUserId())).collect(Collectors.toList());
        }
        return comments;
    }

    private void validateIdentityLinkArguments(String identityId, String identityType) {
        if (identityId == null || identityId.length() == 0) {
            throw new FlowableIllegalArgumentException("identityId is null");
        }
        if (!FlowableConstant.IDENTITY_GROUP.equals(identityType) && !FlowableConstant.IDENTITY_USER.equals(identityType)) {
            throw new FlowableIllegalArgumentException("type must be group or user");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTaskIdentityLink(IdentityRequest taskIdentityRequest) {
        Task task = getTaskNotNull(taskIdentityRequest.getTaskId());
        validateIdentityLinkArguments(taskIdentityRequest.getIdentityId(), taskIdentityRequest.getIdentityType());
        if (FlowableConstant.IDENTITY_GROUP.equals(taskIdentityRequest.getIdentityType())) {
            taskService.addGroupIdentityLink(task.getId(), taskIdentityRequest.getIdentityId(),
                    IdentityLinkType.CANDIDATE);
        } else if (FlowableConstant.IDENTITY_USER.equals(taskIdentityRequest.getIdentityType())) {
            taskService.addUserIdentityLink(task.getId(), taskIdentityRequest.getIdentityId(),
                    IdentityLinkType.CANDIDATE);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTaskIdentityLink(String taskId, String identityId, String identityType) {
        Task task = getTaskNotNull(taskId);
        validateIdentityLinkArguments(identityId, identityType);
        if (FlowableConstant.IDENTITY_GROUP.equals(identityType)) {
            taskService.deleteGroupIdentityLink(task.getId(), identityId, IdentityLinkType.CANDIDATE);
        } else if (FlowableConstant.IDENTITY_USER.equals(identityType)) {
            taskService.deleteUserIdentityLink(task.getId(), identityId, IdentityLinkType.CANDIDATE);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void readTask(TaskRequest taskRequest) {
        String[] taskIds = taskRequest.getTaskIds();
        if (taskIds == null || taskIds.length == 0) {
            throw new FlowableException("taskIds is null or empty");
        }
		String userId = SecurityUtils.getCurrentUserId();
        for (String taskId : taskIds) {
            managementService.executeCommand(new CompleteTaskReadCmd(taskId, userId));
        }
    }
}