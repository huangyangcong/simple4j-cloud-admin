package com.ruoyi.flowable.domain;




import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;


/**
 * @author kubilewang
 * @date 2020年3月23日
 */
@Data
@TableName("T_FLOWABLE_FORM")
public class FlowableForm extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId
    private String formKey;


    private String formName;

    private String formJson;
}