package com.matthewjohnson42.memexService.data.mongo.repository;

import com.matthewjohnson42.memexService.data.Repository;
import com.matthewjohnson42.memexService.data.mongo.entity.RawTextMongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;

/**
 * Data access object for the Mongo collection corresponding to the RawText entity described by RawTextMongo
 */
public interface RawTextMongoRepo extends MongoRepository<RawTextMongo, String>, Repository<RawTextMongo, String> {

    @Query("{createDateTime: {$gt: ?0, $lt: ?1}}")
    Page<RawTextMongo> getByCreateDateTimeRange(LocalDateTime startCreateDate, LocalDateTime endCreateDate, Pageable pageable);

}
