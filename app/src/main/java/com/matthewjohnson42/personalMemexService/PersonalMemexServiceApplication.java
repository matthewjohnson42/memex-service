package com.matthewjohnson42.personalMemexService;

import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.List;

/**
 * A simple application for storing text data in a Mongo repository
 */
@EnableMongoRepositories
@SpringBootApplication
public class PersonalMemexServiceApplication {

	public static void main(String[] args) {
//		Provider[] providers = Security.getProviders();
//		for (int i = 0; i < providers.length; i++) {
//			Security.removeProvider(providers[i].getName());
//			providers[i] = null;
//		}
		Security.addProvider(BouncyCastleProviderSingleton.getInstance());
		SpringApplication.run(PersonalMemexServiceApplication.class);
	}

}
