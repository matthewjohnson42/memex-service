package com.matthewjohnson42.personalMemexService.data.mongo.repository;

import com.matthewjohnson42.personalMemexService.data.Repository;
import com.matthewjohnson42.personalMemexService.data.mongo.entity.RawTextMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Data access object for the Mongo collection corresponding to the RawText entity described by RawTextMongo
 */
public interface RawTextMongoRepo extends MongoRepository<RawTextMongo, String>, Repository<RawTextMongo, String> {
}
