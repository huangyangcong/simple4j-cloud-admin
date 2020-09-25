package com.simple4j.gen.request;


import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simple4j.gen.request.CodeUpdateRequest;
/**
 * 代码生成表新增请求实体类
 *
 * @author hyc
 * @since 2020-09-19
 */
@Data
@ApiModel(value = "代码生成表新增请求实体类", description = "代码生成表新增请求实体类")
public class CodeAddOrUpdateRequest extends CodeUpdateRequest {

}
