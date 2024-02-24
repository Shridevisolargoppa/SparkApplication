package com.aliens.CustomerManagement.controller;

import com.aliens.CustomerManagement.service.SparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SparkController {

    @Autowired
    private SparkService sparkService;
    @Value("${spring.data.mongodb.uri}")
    private String uri;
    @GetMapping("/count")
    public ResponseEntity<Long> countAllCustomers() {
        try {
            // Call the service method to get the count of customers
            long count = sparkService.countAllCustomers();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<String> profileData() {
        String profilingResults = sparkService.performDataProfiling();
        return new ResponseEntity<>(profilingResults, HttpStatus.OK);
    }
    @GetMapping("/missingValues")
    public String performDataProfiling() {
        return sparkService.performMissingValueHandlingAndDataProfiling();
    }

}