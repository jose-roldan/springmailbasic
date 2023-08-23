package com.example.springjpahib.service;

import com.example.springjpahib.model.Customer;
import com.example.springjpahib.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public void crudCustomer() {
        // Save a new customer
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("John");
        newCustomer.setLastName("Doe");

        repository.save(newCustomer);

        // Find a customer by ID
        Optional<Customer> result = repository.findById(1L);
        result.ifPresent(customer -> System.out.println(customer));

        // Find customers by last name
        List<Customer> customers = repository.findByLastName("Doe");
        customers.forEach(customer -> System.out.println(customer));

        // List all customers
        Iterable<Customer> iterator = repository.findAll();
        iterator.forEach(customer -> System.out.println(customer));

        // Count number of customer
        long count = repository.count();
        System.out.println("Number of customers: " + count);
    }
}
