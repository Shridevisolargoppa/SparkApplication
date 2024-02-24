package com.aliens.CustomerManagement.controller;

import com.aliens.CustomerManagement.exception.CustomerException;
import com.aliens.CustomerManagement.feign.ProducerClient;
import com.aliens.CustomerManagement.model.Customer;
import com.aliens.CustomerManagement.repository.CustomerRepository;
import com.aliens.CustomerManagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private ProducerClient producerClient;

    @PostMapping("/create")
    public ResponseEntity<Customer>  create(@RequestBody Customer customer, @RequestHeader("Authorization") String token)
    {
        ResponseEntity<String> resulttoken = producerClient.validate(token);
        if (resulttoken.getBody().equals("Given token is valid")){
        Customer createdCustomer=customerService.create(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);}
        return null;

    }
    @GetMapping("/getallUsers")
    public List<Customer> getall()
    {
        List<Customer> customers=customerService.getall();
        return customers;
    }

    @GetMapping("{custome_id}")
    public ResponseEntity<Customer> getById(@PathVariable int custome_id)
    {
        try {
            Customer get=customerService.getById(custome_id);
            return new ResponseEntity<>(get,HttpStatus.OK);
        }
        catch (CustomerException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }
    @DeleteMapping("{customerId}")
    public ResponseEntity<String> delete(@PathVariable int customerId) throws CustomerException {
        try {
            boolean deleted=customerService.delete(customerId);
            if(deleted)
            { customerRepository.deleteByCustomerId(customerId);
                return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
            }
        } catch (CustomerException e) {
            throw new CustomerException("invalid Customer");
        }
        String notFoundMessage = "Customer with ID " + customerId + " not found.";
        return new ResponseEntity<>(notFoundMessage, HttpStatus.NOT_FOUND);
    }
    @PutMapping("{customerId}")
    public Customer update(@PathVariable int customerId,@RequestBody Customer customer) throws CustomerException {
        return customerService.update(customerId,customer);
    }
}
