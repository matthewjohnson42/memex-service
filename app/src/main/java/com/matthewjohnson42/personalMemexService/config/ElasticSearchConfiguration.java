package com.matthewjohnson42.personalMemexService.config;

import com.matthewjohnson42.personalMemexService.data.elasticsearch.repository.RawTextESRestTemplate;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticSearchConfiguration extends AbstractElasticsearchConfiguration {

    Logger logger = LoggerFactory.getLogger(ElasticSearchConfiguration.class);

    @Value("${elasticsearch.hostname}")
    private String elasticHostname;

    @Value("${elasticsearch.port}")
    private String elasticPort;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        return RestClients.create(ClientConfiguration.create(String.format("%s:%s", elasticHostname, elasticPort))).rest();
    }

    @Bean
    public RawTextESRestTemplate elasticSearchRestTemplate() {
        return new RawTextESRestTemplate(elasticHostname, elasticPort);
    }

}
