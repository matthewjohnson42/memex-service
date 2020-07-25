package com.matthewjohnson42.personalMemexService.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticSearchConfiguration extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.hostname}")
    private String elasticHostname;

    @Value("${elasticsearch.port}")
    private String elasticPort;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        return RestClients.create(ClientConfiguration.create(String.format("%s:%s", elasticHostname, elasticPort))).rest();
    }

}
