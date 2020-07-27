package com.matthewjohnson42.personalMemexService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * A simple application for storing text data in a Mongo repository
 */
@EnableMongoRepositories
@SpringBootApplication
public class PersonalMemexServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalMemexServiceApplication.class);
	}

	// todo add authorization via authenticated credentials

}
