package com.aliens.CustomerManagement.service;

import com.aliens.CustomerManagement.model.Customer;
import com.aliens.CustomerManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BulkService {
    @Autowired
    private GridFsTemplate template;

    @Autowired
    private CustomerRepository customerRepository;

    public String processCustomerDetails(MultipartFile file) throws IOException {
        List<Customer> customers = parseCSV(file);
        customerRepository.saveAll(customers);
        return "Customer details processed and saved successfully.";
    }
    private List<Customer> parseCSV(MultipartFile file) throws IOException {
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line ;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int customerId = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                long phone = Long.parseLong(parts[3]);
                String address = parts[4];
                String companyName = parts[5];
                String industryType = parts[6];
                String customerStatus = parts[7];
                String accountManager = parts[8];
                Customer customer = new Customer(customerId, name, email, phone, address, companyName, industryType, customerStatus, accountManager);
                customers.add(customer);
            }
        }

        return customers;
    }
}
