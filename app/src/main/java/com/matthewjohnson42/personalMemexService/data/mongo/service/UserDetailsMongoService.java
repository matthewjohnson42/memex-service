package com.matthewjohnson42.personalMemexService.data.mongo.service;

import com.matthewjohnson42.personalMemexService.data.converter.UserDetailsMongoConverter;
import com.matthewjohnson42.personalMemexService.data.dto.UserDetailsDto;
import com.matthewjohnson42.personalMemexService.data.mongo.entity.UserDetailsMongo;
import com.matthewjohnson42.personalMemexService.data.mongo.repository.UserDetailsMongoRepo;
import com.matthewjohnson42.personalMemexService.data.DataService;
import org.springframework.stereotype.Service;

/**
 * Data Service for implementing user details persistence logic
 */
@Service
public class UserDetailsMongoService extends DataService<String, UserDetailsDto, UserDetailsMongo> {

    public UserDetailsMongoService(UserDetailsMongoConverter converter, UserDetailsMongoRepo userDetailsMongoRepo) {
        super(converter, userDetailsMongoRepo);
    }

    // password not loaded into DTO by converter
    public String getEncodedPasswordForUser(String username) {
        UserDetailsMongo userDetailsMongo = getIfExists(username);
        return userDetailsMongo.getPassword();
    }

}
