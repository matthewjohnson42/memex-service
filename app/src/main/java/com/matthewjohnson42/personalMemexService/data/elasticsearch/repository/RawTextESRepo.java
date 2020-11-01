package com.matthewjohnson42.personalMemexService.data.elasticsearch.repository;

import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextES;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDateTime;

// Elastic Search query ref doc, in order of usage in search query; is valid JSON
// https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-bool-query.html
// https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-match-query.html
// https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-range-query.html
public interface RawTextESRepo extends ElasticsearchRepository<RawTextES, String> {

    /**
     * Performs a search of ElasticSearch for the search string with hamming distance/Levenshtein distance/fuzziness
     * of 2 within the given date constraints
     */
    // unescaped query string for beautification (EG, via the JSON beautifier at https://codebeautify.org/jsonviewer)
    // {"query":{"bool":{"must":{"match":{"textContent":{"query":"?0","fuzziness":2}}},"filter":[{"range":{"createDateTime":{"gte":"?1","lte":"?2"}}},{"range":{"updateDateTime":{"gte":"?3","lte":"?4"}}}]}}}
    @Query("{\"query\":{\"bool\":{\"must\":{\"match\":{\"textContent\":{\"query\":\"?0\",\"fuzziness\":2}}}," +
            "\"filter\":[{\"range\":{\"createDateTime\":{\"gte\":\"?1\",\"lte\":\"?2\"}}},{\"range\":{" +
            "\"updateDateTime\":{\"gte\":\"?3\",\"lte\":\"?4\"}}}]}}}")
    public Page<RawTextES> getPageFromSearchString(String searchString,
                                                   LocalDateTime startCreateDate,
                                                   LocalDateTime endCreateDate,
                                                   LocalDateTime startUpdateDate,
                                                   LocalDateTime endUpdateDate,
                                                   Pageable pageable);

    @Query("{\"function_score\": {" +
            "\"query\": {" +
            "\"fuzzy\": {" +
            "\"textContent\": { \"value\": \"?0\" } } } } }")
    public Page<RawTextES> getPageFromSearchString(String searchString, Pageable pageable);

}
