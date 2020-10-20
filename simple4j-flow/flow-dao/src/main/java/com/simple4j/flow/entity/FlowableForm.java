package com.simple4j.flow.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.simple4j.autoconfigure.mybatis.base.BaseEntity;
import lombok.Data;

/**
 * @author hyc
 */
@Data
@TableName("T_FLOWABLE_FORM")
public class FlowableForm extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId
    @NotNull
    @Max(value = 100)
    private String formKey;

    @NotNull
	@Max(value = 100)
    private String formName;

    private String formJson;
}