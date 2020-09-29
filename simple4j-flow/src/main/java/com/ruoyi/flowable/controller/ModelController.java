package com.ruoyi.flowable.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.Result;
import com.ruoyi.common.utils.ObjectUtils;
import com.ruoyi.flowable.common.BaseFlowableController;
import com.ruoyi.flowable.common.FlowablePage;
import com.ruoyi.flowable.common.ResponseFactory;
import com.ruoyi.flowable.common.cmd.DeployModelCmd;
import com.ruoyi.flowable.common.cmd.SaveModelEditorCmd;
import com.ruoyi.flowable.vo.ModelRequest;
import com.ruoyi.flowable.vo.ModelResponse;
import com.ruoyi.flowable.wapper.ModelListWrapper;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.common.engine.api.query.QueryProperty;
import org.flowable.engine.impl.ModelQueryProperty;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ModelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flowable/model")
public class ModelController extends BaseFlowableController {
    @Autowired
    protected ResponseFactory responseFactory;
    @Autowired
    protected ObjectMapper objectMapper;

    private static Map<String, QueryProperty> ALLOWED_SORT_PROPERTIES = new HashMap<>();

    static {
        ALLOWED_SORT_PROPERTIES.put("id", ModelQueryProperty.MODEL_ID);
        ALLOWED_SORT_PROPERTIES.put("category", ModelQueryProperty.MODEL_CATEGORY);
        ALLOWED_SORT_PROPERTIES.put("createTime", ModelQueryProperty.MODEL_CREATE_TIME);
        ALLOWED_SORT_PROPERTIES.put("key", ModelQueryProperty.MODEL_KEY);
        ALLOWED_SORT_PROPERTIES.put("lastUpdateTime", ModelQueryProperty.MODEL_LAST_UPDATE_TIME);
        ALLOWED_SORT_PROPERTIES.put("name", ModelQueryProperty.MODEL_NAME);
        ALLOWED_SORT_PROPERTIES.put("version", ModelQueryProperty.MODEL_VERSION);
        ALLOWED_SORT_PROPERTIES.put("tenantId", ModelQueryProperty.MODEL_TENANT_ID);
    }

    @GetMapping(value = "/list")
    public Result getModels(@RequestParam Map<String, String> requestParams) {
        ModelQuery modelQuery = repositoryService.createModelQuery();

        if (ObjectUtils.isNotEmpty(requestParams.get("id"))) {
            modelQuery.modelId(requestParams.get("id"));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get("category"))) {
            modelQuery.modelCategoryLike(ObjectUtils.convertToLike(requestParams.get("category")));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get("name"))) {
            modelQuery.modelNameLike(ObjectUtils.convertToLike(requestParams.get("name")));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get("key"))) {
            modelQuery.modelKey(requestParams.get("key"));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get("version"))) {
            modelQuery.modelVersion(ObjectUtils.convertToInteger(requestParams.get("version")));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get("latestVersion"))) {
            boolean isLatestVersion = ObjectUtils.convertToBoolean(requestParams.get("latestVersion"));
            if (isLatestVersion) {
                modelQuery.latestVersion();
            }
        }
        if (ObjectUtils.isNotEmpty(requestParams.get("deploymentId"))) {
            modelQuery.deploymentId(requestParams.get("deploymentId"));
        }
        if (ObjectUtils.isNotEmpty(requestParams.get("deployed"))) {
            boolean isDeployed = ObjectUtils.convertToBoolean(requestParams.get("deployed"));
            if (isDeployed) {
                modelQuery.deployed();
            } else {
                modelQuery.notDeployed();
            }
        }
        if (ObjectUtils.isNotEmpty(requestParams.get("tenantId"))) {
            modelQuery.modelTenantId(requestParams.get("tenantId"));
        }

        FlowablePage page = this.pageList(requestParams, modelQuery, ModelListWrapper.class, ALLOWED_SORT_PROPERTIES);
        return Result.ok(page);
    }

    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) throws UnsupportedEncodingException {
        Model model = getModelById(id);
        ModelResponse modelResponse = responseFactory.createModelResponse(model);
        if(model.hasEditorSource()){
            byte[] editorBytes = repositoryService.getModelEditorSource(model.getId());
            String editor = new String(editorBytes,"UTF-8");
            modelResponse.setEditor(editor);
        }
        return Result.ok(modelResponse);
    }

    protected Model getModelById(String modelId) {
        Model model = repositoryService.getModel(modelId);
        if (model == null) {
            throw new FlowableObjectNotFoundException("No model found with id " + modelId);
        }
        return model;
    }

    protected void checkModelKeyExists(String modelKey) {
        long countNum = repositoryService.createModelQuery().modelKey(modelKey).count();
        if (countNum > 0) {
            throw new FlowableObjectNotFoundException("ModelKey already exists with id " + modelKey);
        }
    }

    @Log(title = "新增流程模型")
    @PreAuthorize("@ss.hasPermi('flowable:model:save')")
    @PostMapping(value = "/save")
    @Transactional(rollbackFor = Exception.class)
    public Result save(@RequestBody ModelRequest modelRequest) {
        checkModelKeyExists(modelRequest.getKey());

        Model model = repositoryService.newModel();
        model.setKey(modelRequest.getKey());
        model.setName(modelRequest.getName());
        model.setVersion(1);
        model.setMetaInfo(modelRequest.getMetaInfo());
        model.setTenantId(modelRequest.getTenantId());
        model.setCategory(modelRequest.getCategory());
        repositoryService.saveModel(model);

        return Result.ok(responseFactory.createModelResponse(model));
    }

    @Log(title = "修改流程模型")
    @PreAuthorize("@ss.hasPermi('flowable:model:update')")
    @PutMapping(value = "/update")
    @Transactional(rollbackFor = Exception.class)
    public Result update(@RequestBody ModelRequest modelRequest) {
        Model model = getModelById(modelRequest.getId());
        model.setKey(modelRequest.getKey());
        model.setName(modelRequest.getName());
        model.setMetaInfo(modelRequest.getMetaInfo());
        model.setTenantId(modelRequest.getTenantId());
        model.setCategory(modelRequest.getCategory());
        repositoryService.saveModel(model);

        return Result.ok(responseFactory.createModelResponse(model));
    }

    @Log(title = "删除流程模型")
    @PreAuthorize("@ss.hasPermi('flowable:model:delete')")
    @DeleteMapping(value = "/delete")
    @Transactional(rollbackFor = Exception.class)
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        for (String id : idsArr) {
            Model model = getModelById(id);
            List<Model> models = repositoryService.createModelQuery().modelKey(model.getKey()).list();
            for (Model deleteModel : models) {
                repositoryService.deleteModel(deleteModel.getId());
            }
        }

        return Result.ok();
    }

    @Log(title = "保存流程设计")
    @PreAuthorize("@ss.hasPermi('flowable:model:saveModelEditor')")
    @PutMapping(value = "/saveModelEditor")
    @Transactional(rollbackFor = Exception.class)
    public Result saveModelEditor(@RequestBody ModelRequest modelRequest) {
        managementService.executeCommand(new SaveModelEditorCmd(modelRequest.getId(), modelRequest.getEditor()));
        return Result.ok();
    }

    @Log(title = "部署流程模型")
    @PreAuthorize("@ss.hasPermi('flowable:model:deploy')")
    @PostMapping(value = "/deploy")
    @Transactional(rollbackFor = Exception.class)
    public Result deployModel(@RequestBody ModelRequest modelRequest) {
        Model model = getModelById(modelRequest.getId());
        managementService.executeCommand(new DeployModelCmd(modelRequest.getId()));
        return Result.ok();
    }
}
