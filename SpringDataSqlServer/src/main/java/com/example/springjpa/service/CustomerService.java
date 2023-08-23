package com.example.springjpa.service;

import com.example.springjpa.model.Customer;
import com.example.springjpa.repository.CustomerRepository;
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
        Customer newCustomer01 = new Customer();
        newCustomer01.setFirstName("John");
        newCustomer01.setLastName("Doe");

        repository.save(newCustomer01);

        // Find a customer by ID
        Optional<Customer> result = repository.findById(1L);
        result.ifPresent(customer -> System.out.println(customer));

        // Find customers by last name
        List<Customer> customers = repository.findByLastName("Doe");
        customers.forEach(customer -> System.out.println(customer));

        // Save a new customer 02
//        Customer newCustomer02 = new Customer();
//        newCustomer02.setFirstName("Jeff");
//        newCustomer02.setLastName("Daniel");
//        
//        repository.save(newCustomer02);

        // List all customers
        Iterable<Customer> iterator = repository.findAll();
        iterator.forEach(customer -> System.out.println(customer));

        // Count number of customer
        long count = repository.count();
        System.out.println("Number of customers: " + count);
    }
}
