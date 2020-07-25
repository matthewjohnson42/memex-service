package com.matthewjohnson42.personalMemexService.data.mongo.repository;

import com.matthewjohnson42.personalMemexService.data.mongo.entity.RawTextMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Accessor for Mongo document collection named "rawText"
 */
public interface RawTextMongoRepo extends MongoRepository<RawTextMongo, String> {
}
