package com.matthewjohnson42.personalMemexService.data.mongo.service;

import com.matthewjohnson42.personalMemexService.data.mongo.entity.UserDetailsMongo;
import com.matthewjohnson42.personalMemexService.data.mongo.repository.UserDetailsMongoRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsMongoService implements UserDetailsService {

    private UserDetailsMongoRepo userDetailsMongoRepo;

    public UserDetailsMongoService(UserDetailsMongoRepo userDetailsMongoRepo) {
        this.userDetailsMongoRepo = userDetailsMongoRepo;
    }

    public UserDetails loadUserByUsername(String username) {
        Optional<UserDetailsMongo> userDetailsOptional = userDetailsMongoRepo.findById(username);
        return userDetailsOptional.isPresent() ? userDetailsOptional.get() : new UserDetailsMongo();
    }

}
