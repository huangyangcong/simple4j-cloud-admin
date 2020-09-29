package com.ruoyi.flowable.config;

import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
@Configuration
public class FlowableEngineConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {
    @Value("${kubilewang.flowable.font-name}")
    private String flowableFontName;

    @Override
    public void configure(SpringProcessEngineConfiguration engineConfiguration) {
        engineConfiguration.setProcessDiagramGenerator(processDiagramGenerator());
        engineConfiguration.setActivityFontName(flowableFontName);
        engineConfiguration.setLabelFontName(flowableFontName);
        engineConfiguration.setAnnotationFontName(flowableFontName);
    }

    @Bean
    public ProcessDiagramGenerator processDiagramGenerator() {
        return new CustomProcessDiagramGenerator();
    }
}
