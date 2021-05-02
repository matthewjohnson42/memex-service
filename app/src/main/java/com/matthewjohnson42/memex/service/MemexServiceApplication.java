package com.matthewjohnson42.memex.service;

import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.matthewjohnson42.memex.data.SimpleDto;

import java.security.Security;

/**
 * An application for storing text data in a Mongo database with ElasticSearch for search
 */
@EnableMongoRepositories
@SpringBootApplication
public class MemexServiceApplication {

	public static void main(String[] args) {
		System.out.println(new SimpleDto().getSimpleClassMemberVariable());
		Security.addProvider(BouncyCastleProviderSingleton.getInstance());
		SpringApplication.run(MemexServiceApplication.class);
	}

}
