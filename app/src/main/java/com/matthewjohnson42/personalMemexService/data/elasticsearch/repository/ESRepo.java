package com.matthewjohnson42.personalMemexService.data.elasticsearch.repository;

import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESRepo extends ElasticsearchRepository<RawTextES, String> {
}
