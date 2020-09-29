package com.ruoyi.flowable.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.Result;
import com.ruoyi.flowable.domain.FlowableForm;
import com.ruoyi.flowable.service.FlowableFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.annotation.Log;

/**
 * 流程Controller
 *
 * @author kubilewang
 */
@RestController
@RequestMapping("/flowable/form")
public class FlowableFormController extends BaseController {
    @Autowired
    private FlowableFormService flowableFormService;

    /**
     * 自定义查询列表
     *
     * @param flowableForm
     * @param current
     * @param size
     * @return
     */
    @PreAuthorize("@ss.hasPermi('flowable:form:list')")
    @GetMapping(value = "/list")
    public Result list(FlowableForm flowableForm, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<FlowableForm> pageList = flowableFormService.list(new Page<FlowableForm>(current, size), flowableForm);
        return Result.ok(pageList);
    }

    @PreAuthorize("@ss.hasPermi('flowable:form:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        FlowableForm flowableForm = flowableFormService.getById(id);
        return Result.ok(flowableForm);
    }

    /**
     * @param flowableForm
     * @return
     * @功能：新增
     */
    @Log(title = "新增流程表单")
    @PreAuthorize("@ss.hasPermi('flowable:form:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody FlowableForm flowableForm) {
        flowableFormService.save(flowableForm);
        return Result.ok();
    }

    /**
     * @param flowableForm
     * @return
     * @功能：修改
     */
    @Log(title = "修改流程表单")
    @PreAuthorize("@ss.hasPermi('flowable:form:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody FlowableForm flowableForm) {
        flowableFormService.updateById(flowableForm);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @Log(title = "删除流程表单")
    @PreAuthorize("@ss.hasPermi('flowable:form:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            flowableFormService.removeByIds(Arrays.asList(idsArr));
        } else {
            flowableFormService.removeById(idsArr[0]);
        }
        return Result.ok();
    }
}
