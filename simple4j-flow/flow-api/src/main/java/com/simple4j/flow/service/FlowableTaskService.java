package com.simple4j.flow.service;

import java.util.List;

import com.simple4j.flow.request.AddCommentRequest;
import com.simple4j.flow.request.IdentityRequest;
import com.simple4j.flow.request.TaskRequest;
import com.simple4j.flow.request.TaskUpdateRequest;
import com.simple4j.flow.response.FlowNodeResponse;
import com.simple4j.flow.response.TaskResponse;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;


/**
 * @author 庄金明
 * @date 2020年3月23日
 */
public interface FlowableTaskService {
    /**
     * 查询任务详情
     * /rest/tasks/{taskId}
     * @param taskId
     * @return
     */
    TaskResponse getTask(String taskId);

    /**
     * 查询子任务列表
     * /rest/tasks/{taskId}/subtasks
     * @param taskId
     * @return
     */
    List<TaskResponse> getSubTasks(String taskId);

    /**
     * 修改任务
     * /rest/tasks/{taskId}
     * @param taskUpdateRequest
     * @return
     */
    TaskResponse updateTask(TaskUpdateRequest taskUpdateRequest);

    /**
     * 转办任务
     * /app/rest/tasks/{taskId}/action/assign
     * @param taskRequest
     */
    void assignTask(TaskRequest taskRequest);

    /**
     * 新增任务参与人
     * /app/rest/tasks/{taskId}/action/involve
     * @param taskId
     * @param involveUserId
     */
    void involveUser(String taskId, String involveUserId);

    /**
     * 移除任务参与人
     * /app/rest/tasks/{taskId}/action/remove-involved
     * @param taskId
     * @param involveUserId
     */
    void removeInvolvedUser(String taskId, String involveUserId);

    /**
     * 认领任务
     * /app/rest/tasks/{taskId}/action/claim
     * @param taskRequest
     */
    void claimTask(TaskRequest taskRequest);

    /**
     * 取消认领
     *
     * @param taskRequest
     */
    void unclaimTask(TaskRequest taskRequest);

    /**
     * 新增任务关联人
     *
     * @param task
     * @param userId
     * @param linkType
     */
    void addIdentiyLinkForUser(Task task, String userId, String linkType);

    /**
     * 委派任务
     * /runtime/tasks/{taskId}
     * @param taskRequest
     */
    void delegateTask(TaskRequest taskRequest);

    /**
     * 完成任务
     * /runtime/tasks/{taskId}
     * @param taskRequest
     */
    void completeTask(TaskRequest taskRequest);

    /**
     * 删除任务
     * /history/historic-task-instances/{taskId}
     * @param taskId
     */

    void deleteTask(String taskId);

    /**
     * 终止流程
     *
     * @param taskRequest
     */
    void stopProcessInstance(TaskRequest taskRequest);

    /**
     * 查询可退回节点
     *
     * @param taskId
     * @return
     */
    List<FlowNodeResponse> getBackNodes(String taskId);

    /**
     * 退回任务
     *
     * @param taskRequest
     */
    void backTask(TaskRequest taskRequest);

    /**
     * 查询单一任务详情
     * /runtime/tasks/{taskId}
     * @param taskId
     * @return
     */
    Task getTaskNotNull(String taskId);

    /**
     * 查询单一历史任务详情
     * /query/historic-task-instances
     * @param taskId
     * @return
     */
    HistoricTaskInstance getHistoricTaskInstanceNotNull(String taskId);

    /**
     * 新增过程意见
     * /runtime/tasks/{taskId}/comments
     * @param taskId
     * @param processInstanceId
     * @param userId
     * @param type
     * @param message
     */
    void addComment(AddCommentRequest addCommentRequest);

    /**
     * 查询过程意见
     * /runtime/tasks/{taskId}/comments
     * @param taskId
     * @param processInstanceId
     * @param type
     * @param userId
     * @return
     */
    List<Comment> getComments(String taskId, String processInstanceId, String type, String userId);

    /**
     * 新增任务关联信息
     * /runtime/tasks/{taskId}/identitylinks
     * @param taskIdentityRequest
     */
    void saveTaskIdentityLink(IdentityRequest taskIdentityRequest);

    /**
     * 删除任务关联信息
     * /runtime/tasks/{taskId}/identitylinks/{family}/{identityId}/{type}
     * @param taskId
     * @param identityId
     * @param identityType
     */
    void deleteTaskIdentityLink(String taskId, String identityId, String identityType);

    /**
     * 任务已阅
     * @param taskRequest
     */
    void readTask(TaskRequest taskRequest);
}