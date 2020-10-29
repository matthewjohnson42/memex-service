package com.matthewjohnson42.personalMemexService.data.persistence.elasticsearch.repository;

import com.matthewjohnson42.personalMemexService.data.persistence.elasticsearch.entity.RawTextES;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RawTextESRepo extends ElasticsearchRepository<RawTextES, String> {

    @Query("{\"function_score\": {" +
            "\"query\": {" +
            "\"fuzzy\": {" +
            "\"textContent\": { \"value\": \"?0\" } } } } }")
    public Page<RawTextES> getPageFromSearchString(String searchString, Pageable pageable);

}
