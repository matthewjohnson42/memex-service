package com.matthewjohnson42.personalMemexService;

import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.security.Security;

/**
 * A simple application for storing text data in a Mongo repository
 */
@EnableMongoRepositories
@SpringBootApplication
public class PersonalMemexServiceApplication {

	public static void main(String[] args) {
		Security.addProvider(BouncyCastleProviderSingleton.getInstance());
		SpringApplication.run(PersonalMemexServiceApplication.class);
	}

}
