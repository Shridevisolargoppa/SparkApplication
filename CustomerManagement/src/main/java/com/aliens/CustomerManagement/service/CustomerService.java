package com.aliens.CustomerManagement.service;

import com.aliens.CustomerManagement.exception.CustomerException;
import com.aliens.CustomerManagement.model.Customer;
import com.aliens.CustomerManagement.repository.CustomerRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public Customer create(Customer customer) {
        customer.setAudit(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    public List<Customer> getall() {
        return customerRepository.findAll();
    }

    public Customer getById(int customerId) throws CustomerException {
        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer == null) {
            throw new CustomerException("Customer not found with ID: " + customerId);
        }
        return customer;
    }

    public boolean delete(int customerId) throws CustomerException {
        if (!customerRepository.existsByCustomerId(customerId)) {
            throw new CustomerException("Customer not found with ID: " + customerId);
        }
        customerRepository.deleteByCustomerId(customerId);
        return true;
    }

    public Customer update(int customerId, Customer customer) throws CustomerException {
        Customer existingCustomer = customerRepository.findByCustomerId(customerId);
        if (existingCustomer == null) {
            throw new CustomerException("Customer not found with ID: " + customerId);
        }
        customer.setCustomerId(customerId);
        customer.setAudit(LocalDateTime.now());
        return customerRepository.save(customer);
    }
}
