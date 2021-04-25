package com.matthewjohnson42.memexService.data.elasticsearch.repository;

import com.matthewjohnson42.memexService.config.ElasticSearchConfiguration;
import com.matthewjohnson42.memexService.data.elasticsearch.entity.RawTextES;
import com.matthewjohnson42.memexService.data.elasticsearch.entity.RawTextESComposite;
import com.matthewjohnson42.memexService.data.elasticsearch.entity.wrappers.RawTextESHit;
import com.matthewjohnson42.memexService.data.elasticsearch.entity.wrappers.RawTextESWrapper;
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
 *
 * @see com.matthewjohnson42.memexService.data.Repository
 */
@Component
public class RawTextESRestTemplate extends ElasticRestTemplate<String, RawTextES> {

    Logger logger = LoggerFactory.getLogger(RawTextESRestTemplate.class);

    private String rawTextUrl;
    private String rawTextDocUrl;
    private String rawTextSearchUrl;
    private final String rawTextSearchByIdQuery;
    private final String rawTextSearchByTextContentFuzzyQuery;
    private final String rawTextSearchByTextContentWildcardQuery;

    private final Integer fuzziness = 1;

    public RawTextESRestTemplate(ElasticSearchConfiguration config) {
        super(config.getRawTextCreateIndex());
        this.rawTextSearchByIdQuery = config.getRawTextSearchById();
        this.rawTextSearchByTextContentFuzzyQuery = config.getRawTextSearchByTextContentFuzzy();
        this.rawTextSearchByTextContentWildcardQuery = config.getRawTextSearchByTextContentWildcard();
        rawTextUrl = String.format("http://%s:%s/rawtext", config.getHostName(), config.getHostPort());
        rawTextSearchUrl = String.format("http://%s:%s/rawtext/_search", config.getHostName(), config.getHostPort());
        rawTextDocUrl = String.format("http://%s:%s/rawtext/_doc/{id}", config.getHostName(), config.getHostPort());
    }

    public Optional<RawTextES> findById(String id) {
        String query = String.format(rawTextSearchByIdQuery, id);
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

    public Page<RawTextESComposite> getPageFromSearchString(
            String searchString,
            LocalDateTime startCreateDate,
            LocalDateTime endCreateDate,
            LocalDateTime startUpdateDate,
            LocalDateTime endUpdateDate,
            Pageable pageable) {
        String query = getSearchQuery(searchString, startCreateDate, endCreateDate, startUpdateDate, endUpdateDate, pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity(query, headers);
        RawTextESWrapper responseBody = postForObject(rawTextSearchUrl, request, RawTextESWrapper.class);
        Integer totalHits = responseBody.getHits().getTotal().getValue();
        List<RawTextESComposite> pageContent = new ArrayList<>();
        for (RawTextESHit hit : responseBody.getHits().getHits()) {
            RawTextESComposite rawTextESComposite = new RawTextESComposite(hit.get_source());
            rawTextESComposite.setHighlights(hit.getHighlight().getTextContent());
            pageContent.add(rawTextESComposite);
        }
        return new PageImpl<>(pageContent, pageable, totalHits);
    }

    private String getSearchQuery(String searchString,
                                  LocalDateTime startCreateDate,
                                  LocalDateTime endCreateDate,
                                  LocalDateTime startUpdateDate,
                                  LocalDateTime endUpdateDate,
                                  Pageable pageable) {
        Assert.hasLength(searchString, "Search string cannot be null or the empty string");
        Assert.notNull(pageable, "Pageable cannot be null");
        Integer startIndex = pageable.getPageNumber() * pageable.getPageSize();
        Integer pageSize = pageable.getPageSize();
        String startCreate = startCreateDate == null ? "null" : "\"" + dateTimeFormatter.format(startCreateDate) + "\"";
        String endCreate = endCreateDate == null ? "null" : "\"" + dateTimeFormatter.format(endCreateDate) + "\"";
        String startUpdate = startUpdateDate == null ? "null" : "\"" + dateTimeFormatter.format(startUpdateDate) + "\"";
        String endUpdate = endUpdateDate == null ? "null" : "\"" + dateTimeFormatter.format(endUpdateDate) + "\"";
        if (searchString.matches("[^\\\\]\\*")) {
            logger.info("Search string matches wildcard regex");
            return String.format(rawTextSearchByTextContentWildcardQuery,
                    startIndex, pageSize, searchString, startCreate, endCreate, startUpdate, endUpdate, searchString);
        } else {
            logger.info("Search string does not match wildcard regex");
            return String.format(rawTextSearchByTextContentFuzzyQuery,
                    startIndex, pageSize, searchString, fuzziness, startCreate, endCreate, startUpdate, endUpdate);
        }
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
            HttpEntity<String> request = new HttpEntity(createIndexCommand, headers);
            put(rawTextUrl, request);
            logger.info("Created ElasticSearch index \"rawText\"");
        }
    }

}
