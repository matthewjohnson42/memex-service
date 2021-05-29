package com.matthewjohnson42.memex.service.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RawTextElasticConfiguration extends AbstractElasticConfiguration {

    public RawTextElasticConfiguration() {
        this.createIndexResourceFile = "elasticsearchqueries/rawTextCreateIndex.json";
    }

    public String getRawTextSearchById() {
        return readResource("elasticsearchqueries/rawTextSearchById.json");
    }

    public String getRawTextSearchByTextContentFuzzy() {
        return readResource("elasticsearchqueries/rawTextSearchByTextContentFuzzy.json");
    }

}
