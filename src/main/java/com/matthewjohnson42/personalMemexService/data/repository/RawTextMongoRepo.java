package com.matthewjohnson42.personalMemexService.data.repository;

import com.matthewjohnson42.personalMemexService.data.entity.RawTextEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Accessor for Mongo document collection named "rawText"
 */
public interface RawTextMongoRepo extends MongoRepository<RawTextEntity, String> {
}
