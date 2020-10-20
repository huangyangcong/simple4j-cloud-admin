package com.simple4j.flow.response;

import java.util.List;

import lombok.Data;

@Data
public class CategoryResponse {
    private String category;
    private String categoryName;
    private List<ProcessDefinitionVoResponse> processDefinitionVoList;
}
