package com.matthewjohnson42.memexService.data.elasticsearch.repository;

import com.matthewjohnson42.memexService.data.Entity;
import com.matthewjohnson42.memexService.data.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * A rest template for accessing the ElasticSearch index of the generic type
 * @param <E> the type corresponding to the ES index
 *
 * @see com.matthewjohnson42.memexService.data.Repository
 */
public abstract class ElasticRestTemplate<ID, E extends Entity<ID>> extends RestTemplate implements Repository<E , ID> {

    private String format = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    protected DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern(format).toFormatter();

    protected final String createIndexCommand;

    public ElasticRestTemplate(String createIndexCommand) {
        this.createIndexCommand = createIndexCommand;
        initIndex();
    }

    protected abstract void initIndex();

}
