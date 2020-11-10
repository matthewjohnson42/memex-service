package com.matthewjohnson42.personalMemexService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfiguration {

    @Value("${db.elasticsearch.hostname}")
    private String hostName;

    @Value("${db.elasticsearch.port}")
    private String hostPort;

    public String getHostName() {
        return hostName;
    }

    public String getHostPort() {
        return hostPort;
    }

}
