package com.matthewjohnson42.memexService.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
public class ElasticSearchConfiguration {

    Logger logger = LoggerFactory.getLogger(ElasticSearchConfiguration.class);

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

    public String getRawTextCreateIndex() {
        return readResource("elasticsearchqueries/rawTextCreateIndex.json");
    }

    public String getRawTextSearchById() {
        return readResource("elasticsearchqueries/rawTextSearchById.json");
    }

    public String getRawTextSearchByTextContentFuzzy() {
        return readResource("elasticsearchqueries/rawTextSearchByTextContentFuzzy.json");
    }

    private String readResource(String resource) {
        StringBuilder sb = new StringBuilder();
        ClassPathResource classPathResource = new ClassPathResource(resource);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()));
            reader.lines().forEach(sb::append);
        } catch (IOException e) {
            logger.error("Exception when reading from resource.", e);
        }
        return sb.toString();
    }

}
