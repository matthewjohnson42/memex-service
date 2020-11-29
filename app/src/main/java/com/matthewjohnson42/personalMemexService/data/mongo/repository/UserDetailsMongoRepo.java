package com.matthewjohnson42.personalMemexService.data.mongo.repository;

import com.matthewjohnson42.personalMemexService.data.mongo.entity.UserDetailsMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Data access object for the Mongo collection corresponding to the UserDetails entity as described by UserDetailsMongo
 */
public interface UserDetailsMongoRepo extends MongoRepository<UserDetailsMongo, String> {
}
