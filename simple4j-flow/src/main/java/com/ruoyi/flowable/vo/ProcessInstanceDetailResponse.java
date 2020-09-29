package com.ruoyi.flowable.vo;

import lombok.Data;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
@Data
public class ProcessInstanceDetailResponse extends HistoricProcessInstanceResponse {
    private boolean suspended;
    private String deleteReason;
    private String startUserName;

}
