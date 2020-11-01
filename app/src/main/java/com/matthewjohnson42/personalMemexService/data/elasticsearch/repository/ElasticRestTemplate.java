package com.matthewjohnson42.personalMemexService.data.elasticsearch.repository;

import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;

/**
 * A rest template for accessing the ElasticSearch index of the generic type
 * @param <E> the type corresponding to the ES index
 */
public abstract class ElasticRestTemplate<E> extends RestTemplate  {

    private String format = "yyyy-MM-dd'T'HH:mm:ssSS";
    protected DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern(format).toFormatter();

    public abstract Optional<E> save(E e); // both create and update

    public abstract Optional<E> findById(String id);

    public abstract void deleteById(String id);

    public abstract void initIndex();

}
