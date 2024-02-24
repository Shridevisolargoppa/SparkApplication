package com.aliens.CustomerManagement.repository;

import com.aliens.CustomerManagement.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer,String> {
    Customer findByCustomerId(int custome_id);

    boolean existsByCustomerId(int customerId);

    void deleteByCustomerId(int customerId);

}
