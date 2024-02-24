package com.aliens.CustomerManagement.controller;

import com.aliens.CustomerManagement.service.BulkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class BulkController
{
    @Autowired
    BulkService bulkService;

    @PostMapping("/Bulkupload")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CSV file is empty");
        }

        try {
            String response = bulkService.processCustomerDetails(file);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process CSV file: " + e.getMessage());
        }
    }

    }



