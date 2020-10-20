package com.simple4j.flow.response;

import lombok.Data;

/**
 * @author 庄金明
 * @date 2020年3月24日
 */
@Data
public class IdentityResponse {
    private String identityId;
    private String identityType;
    private String identityName;
}
