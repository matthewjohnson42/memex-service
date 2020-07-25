package com.matthewjohnson42.personalMemexService.data.elasticsearch.repository;

import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RawTextESRepo extends ElasticsearchRepository<RawTextES, String> {
}
