package com.ruoyi.flowable.controller;




import com.google.common.collect.ImmutableMap;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.Result;
import com.ruoyi.common.utils.ObjectUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flowable.common.BaseFlowableController;
import com.ruoyi.flowable.common.FlowablePage;
import com.ruoyi.flowable.constant.FlowableConstant;
import com.ruoyi.flowable.service.ProcessDefinitionService;
import com.ruoyi.flowable.vo.ProcessDefinitionRequest;
import com.ruoyi.flowable.vo.ProcessDefinitionResponse;
import com.ruoyi.flowable.wapper.ProcDefListWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.query.QueryProperty;
import org.flowable.engine.impl.ProcessDefinitionQueryProperty;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
@RestController
@RequestMapping("/flowable/processDefinition")
@Slf4j
public class ProcessDefinitionController extends BaseFlowableController {
    private static final Map<String, QueryProperty> ALLOWED_SORT_PROPERTIES = new HashMap<>();
    @Autowired
    private ProcessDefinitionService processDefinitionService;

    static {
        ALLOWED_SORT_PROPERTIES.put(FlowableConstant.ID, ProcessDefinitionQueryProperty.PROCESS_DEFINITION_ID);
        ALLOWED_SORT_PROPERTIES.put(FlowableConstant.KEY, ProcessDefinitionQueryProperty.PROCESS_DEFINITION_KEY);
        ALLOWED_SORT_PROPERTIES.put(FlowableConstant.CATEGORY,
                ProcessDefinitionQueryProperty.PROCESS_DEFINITION_CATEGORY);
        ALLOWED_SORT_PROPERTIES.put(FlowableConstant.NAME, ProcessDefinitionQueryProperty.PROCESS_DEFINITION_NAME);
        ALLOWED_SORT_PROPERTIES.put(FlowableConstant.VERSION,
                ProcessDefinitionQueryProperty.PROCESS_DEFINITION_VERSION);
        ALLOWED_SORT_PROPERTIES.put(FlowableConstant.TENANT_ID,
                ProcessDefinitionQueryProperty.PROCESS_DEFINITION_TENANT_ID);
    }

    @PreAuthorize("@ss.hasPermi('flowable:processDefinition:list')")
    @GetMapping(value = "/list")
    public Result list(@RequestParam Map<String, String> requestParams) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.ID))) {
            processDefinitionQuery.processDefinitionId(requestParams.get(FlowableConstant.ID));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.CATEGORY))) {
            processDefinitionQuery.processDefinitionCategoryLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.CATEGORY)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.KEY))) {
            processDefinitionQuery.processDefinitionKeyLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.KEY)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.NAME))) {
            processDefinitionQuery.processDefinitionNameLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.NAME)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.VERSION))) {
            processDefinitionQuery.processDefinitionVersion(ObjectUtils.convertToInteger(requestParams.get(FlowableConstant.VERSION)));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.SUSPENDED))) {
            boolean suspended = ObjectUtils.convertToBoolean(requestParams.get(FlowableConstant.SUSPENDED));
            if (suspended) {
                processDefinitionQuery.suspended();
            } else {
                processDefinitionQuery.active();
            }
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.LATEST_VERSION))) {
            boolean latest = ObjectUtils.convertToBoolean(requestParams.get(FlowableConstant.LATEST_VERSION));
            if (latest) {
                processDefinitionQuery.latestVersion();
            }
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.STARTABLE_BY_USER))) {
            processDefinitionQuery.startableByUser(requestParams.get(FlowableConstant.STARTABLE_BY_USER));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.TENANT_ID))) {
            processDefinitionQuery.processDefinitionTenantId(requestParams.get(FlowableConstant.TENANT_ID));
        }
        FlowablePage page = this.pageList(requestParams, processDefinitionQuery, ProcDefListWrapper.class,
                ALLOWED_SORT_PROPERTIES);
        return Result.ok(page);
    }

    @GetMapping(value = "/listMyself")
    public Result listMyself(@RequestParam Map<String, String> requestParams) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        if (ObjectUtils.isNotEmpty(requestParams.get(FlowableConstant.NAME))) {
            processDefinitionQuery.processDefinitionNameLike(ObjectUtils.convertToLike(requestParams.get(FlowableConstant.NAME)));
        }
        processDefinitionQuery.latestVersion().active().startableByUser(SecurityUtils.getUserId());
        FlowablePage page = this.pageList(requestParams, processDefinitionQuery, ProcDefListWrapper.class,
                ALLOWED_SORT_PROPERTIES);
        return Result.ok(page);
    }

    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String processDefinitionId) {
        permissionService.validateReadPermissionOnProcessDefinition(SecurityUtils.getUserId(), processDefinitionId);
        ProcessDefinition processDefinition = processDefinitionService.getProcessDefinitionById(processDefinitionId);
        String formKey = null;
        if (processDefinition.hasStartFormKey()) {
            formKey = formService.getStartFormKey(processDefinitionId);
        }
        ProcessDefinitionResponse processDefinitionResponse =
                responseFactory.createProcessDefinitionResponse(processDefinition, formKey);
        return Result.ok(processDefinitionResponse);
    }

    @GetMapping(value = "/renderedStartForm")
    public Result renderedStartForm(@RequestParam String processDefinitionId) {
        permissionService.validateReadPermissionOnProcessDefinition(SecurityUtils.getUserId(), processDefinitionId);
        Object renderedStartForm = formService.getRenderedStartForm(processDefinitionId);
        boolean showBusinessKey = this.isShowBusinessKey(processDefinitionId);
        return Result.ok(ImmutableMap.of("renderedStartForm", renderedStartForm, "showBusinessKey", showBusinessKey));
    }

    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> image(@RequestParam String processDefinitionId) {
        permissionService.validateReadPermissionOnProcessDefinition(SecurityUtils.getUserId(), processDefinitionId);
        ProcessDefinition processDefinition = processDefinitionService.getProcessDefinitionById(processDefinitionId);
        InputStream imageStream = repositoryService.getProcessDiagram(processDefinition.getId());
        if (imageStream == null) {
            throw new FlowableException(messageFormat("Process definition image is not found with id {0}",
                    processDefinitionId));
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.IMAGE_PNG);
        try {
            return new ResponseEntity<>(IOUtils.toByteArray(imageStream), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            throw new FlowableException(messageFormat("Process definition image read error with id {0}",
                    processDefinitionId), e);
        }
    }

    @PreAuthorize("@ss.hasPermi('flowable:processDefinition:xml')")
    @GetMapping(value = "/xml")
    public ResponseEntity<byte[]> xml(@RequestParam String processDefinitionId) {
        permissionService.validateReadPermissionOnProcessDefinition(SecurityUtils.getUserId(), processDefinitionId);
        ProcessDefinition processDefinition = processDefinitionService.getProcessDefinitionById(processDefinitionId);
        String deploymentId = processDefinition.getDeploymentId();
        String resourceId = processDefinition.getResourceName();
        if (deploymentId == null || deploymentId.length() == 0) {
            throw new FlowableException(messageFormat("Process definition deployment id is not found with id {0}",
                    processDefinitionId));
        }
        if (resourceId == null || resourceId.length() == 0) {
            throw new FlowableException(messageFormat("Process definition resource id is not found with id {0}",
                    processDefinitionId));
        }
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
        if (deployment == null) {
            throw new FlowableException(messageFormat("Process definition deployment is not found with deploymentId " +
                    "{0}", deploymentId));
        }

        List<String> resourceList = repositoryService.getDeploymentResourceNames(deploymentId);
        if (ObjectUtils.isEmpty(resourceList) || !resourceList.contains(resourceId)) {
            throw new FlowableException(messageFormat("Process definition resourceId {0} is not found with " +
                    "deploymentId {1}", resourceId, deploymentId));
        }
        InputStream resourceStream = repositoryService.getResourceAsStream(deploymentId, resourceId);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_XML);
        try {
            return new ResponseEntity<>(IOUtils.toByteArray(resourceStream), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取流程定义XML信息异常", e);
            throw new FlowableException(messageFormat("ProcessDefinition xml read error with id {0}", deploymentId), e);
        }
    }

    @Log(title = "删除流程定义")
    @PreAuthorize("@ss.hasPermi('flowable:processDefinition:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String processDefinitionId, @RequestParam(required = false, defaultValue =
            "false") Boolean cascade) {
        processDefinitionService.delete(processDefinitionId, cascade);
        return Result.ok();
    }

    @Log(title = "激活流程定义")
    @PreAuthorize("@ss.hasPermi('flowable:processDefinition:suspendOrActivate')")
    @PutMapping(value = "/activate")
    public Result activate(@RequestBody ProcessDefinitionRequest actionRequest) {
        processDefinitionService.activate(actionRequest);
        return Result.ok();
    }

    @Log(title = "挂起流程定义")
    @PreAuthorize("@ss.hasPermi('flowable:processDefinition:suspendOrActivate')")
    @PutMapping(value = "/suspend")
    public Result suspend(@RequestBody ProcessDefinitionRequest actionRequest) {
        processDefinitionService.suspend(actionRequest);
        return Result.ok();
    }

    /**
     * 导入流程定义
     *
     * @param request
     * @return
     */
    @Log(title = "导入流程定义")
    @PreAuthorize("@ss.hasPermi('flowable:processDefinition:import')")
    @PostMapping(value = "/import")
    public Result doImport(@RequestParam(required = false) String tenantId, HttpServletRequest request) {
        processDefinitionService.doImport(tenantId, request);
        return Result.ok();
    }
}
