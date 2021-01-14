package com.matthewjohnson42.personalMemexService.data.elasticsearch.repository;

import com.matthewjohnson42.personalMemexService.config.ElasticSearchConfiguration;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextES;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.wrappers.RawTextESHit;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.wrappers.RawTextESWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Custom implementation of an ElasticSearch REST client. Used for accessing the ElasticSearch data store.
 * Chosen to address the failure of ElasticSearch to recognize complex, base 64 encoded queries submitted by
 * spring-data-elasticsearch.
 */
@Component
public class RawTextESRestTemplate extends ElasticRestTemplate<String, RawTextES> {

    Logger logger = LoggerFactory.getLogger(RawTextESRestTemplate.class);

    private String rawTextUrl;
    private String rawTextDocUrl;
    private String rawTextSearchUrl;
    private final String findByIdQuery = "{\"query\":{\"term\":{\"id\":\"%s\"}}}\n";
    private final String searchByContentWithDateQuery = "{\"from\":%s,\"size\":%s,\"query\":{\"bool\":{\"must\":" +
            "{\"match\":{\"textContent\":{\"query\":\"%s\",\"fuzziness\":%s}}},\"filter\":[{\"range\":" +
            "{\"createDateTime\":{\"gte\":%s,\"lte\":%s}}},{\"range\":{\"updateDateTime\":{\"gte\":%s,\"lte\":%s}}}" +
            "]}},\"sort\":[{\"_score\":{\"order\":\"desc\"}}]}";
    private final String rawTextIndexCreateQuery = "{\"mappings\":{\"properties\":{\"id\":{\"type\":\"text\"," +
            "\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"textContent\":{\"type\":" +
            "\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"createDateTime\":{" +
            "\"type\":\"date\",\"format\":\"strict_date_hour_minute_second_millis\"},\"updateDateTime\":{\"type\":" +
            "\"date\",\"format\":\"strict_date_hour_minute_second_millis\"}}}}";

    public RawTextESRestTemplate(ElasticSearchConfiguration config) {
        rawTextUrl = String.format("http://%s:%s/rawtext", config.getHostName(), config.getHostPort());
        rawTextSearchUrl = String.format("http://%s:%s/rawtext/_search", config.getHostName(), config.getHostPort());
        rawTextDocUrl = String.format("http://%s:%s/rawtext/_doc/{id}", config.getHostName(), config.getHostPort());
        initIndex();
    }

    public Optional<RawTextES> findById(String id) {
        String query = String.format(findByIdQuery, id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestBody = new HttpEntity<>(query, headers);
        RawTextESWrapper responseBody = postForObject(rawTextSearchUrl, requestBody, RawTextESWrapper.class);
        if (responseBody.getHits().getHits().size() > 0) {
            return Optional.of(responseBody.getHits().getHits().get(0).get_source());
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(String id) {
        delete(rawTextDocUrl, id);
    }

    public RawTextES save(RawTextES rawTextES) {
        String rawTextId = rawTextES.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RawTextES> requestBody = new HttpEntity(rawTextES, headers);
        put(rawTextDocUrl, requestBody, rawTextId);
        return rawTextES; // do not use "findIfExists" to validate stored value given HTTP request latency and non-blocking nature
    }

    public Page<RawTextES> getPageFromSearchString(
            String searchString,
            LocalDateTime startCreateDate,
            LocalDateTime endCreateDate,
            LocalDateTime startUpdateDate,
            LocalDateTime endUpdateDate,
            Pageable pageable) {
        Assert.hasLength(searchString, "Search string cannot be null or the empty string");
        Assert.notNull(pageable, "Pageable cannot be null");
        Integer startIndex = pageable.getPageNumber() * pageable.getPageSize();
        Integer pageSize = pageable.getPageSize();
        Integer fuzziness = 2;
        String startCreate = startCreateDate == null ? "null" : "\"" + dateTimeFormatter.format(startCreateDate) + "\"";
        String endCreate = endCreateDate == null ? "null" : "\"" + dateTimeFormatter.format(endCreateDate) + "\"";
        String startUpdate = startUpdateDate == null ? "null" : "\"" + dateTimeFormatter.format(startUpdateDate) + "\"";
        String endUpdate = endUpdateDate == null ? "null" : "\"" + dateTimeFormatter.format(endUpdateDate) + "\"";
        String query = String.format(searchByContentWithDateQuery,
                startIndex,
                pageSize,
                searchString,
                fuzziness,
                startCreate,
                endCreate,
                startUpdate,
                endUpdate);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity(query, headers);
        RawTextESWrapper responseBody = postForObject(rawTextSearchUrl, request, RawTextESWrapper.class);
        Integer totalHits = responseBody.getHits().getTotal().getValue();
        List<RawTextES> pageContent = new ArrayList<>();
        for (RawTextESHit hit : responseBody.getHits().getHits()) {
            pageContent.add(hit.get_source());
        }
        return new PageImpl<>(pageContent, pageable, totalHits);
    }

    public void initIndex() {
        logger.info("Checking for existing ElasticSearch index \"rawText\"");
        boolean indexExists = false;
        try {
            ResponseEntity response = getForEntity(rawTextUrl, String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                indexExists = true;
                logger.info("Found existing ElasticSearch index \"rawText\", will not attempt re-creation of index.");
            }
        } catch (Exception e) {
            if (!(e instanceof HttpClientErrorException.NotFound)) {
                logger.error("Error when checking for ElasticSearch index \"rawText\"", e);
            }
        }
        if (!indexExists) {
            logger.info("Did not find existing ElasticSearch index \"rawText\", proceeding with creation");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity(rawTextIndexCreateQuery, headers);
            put(rawTextUrl, request);
            logger.info("Created ElasticSearch index \"rawText\"");
        }
    }

}
