package com.aliens.CustomerManagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Configuration
@FeignClient(name = "validate-service", url = "http://localhost:8082/user")
public interface ProducerClient {

    @PostMapping("/validateToken")
    ResponseEntity<String> validate(@RequestHeader("Authorization") String token);

}

