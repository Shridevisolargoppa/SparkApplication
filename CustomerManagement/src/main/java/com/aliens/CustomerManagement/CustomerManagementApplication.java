package com.aliens.CustomerManagement;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.ComponentScan;

@EnableMongoRepositories
@EnableFeignClients
@ComponentScan("com.aliens.CustomerManagement.feign.ProductClient")
@SpringBootApplication(scanBasePackages={"com.aliens.CustomerManagement.feign"})
public class CustomerManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagementApplication.class, args);
	}
}