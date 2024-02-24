package com.Validation.TokenValidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TokenValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenValidationApplication.class, args);
	}

}
