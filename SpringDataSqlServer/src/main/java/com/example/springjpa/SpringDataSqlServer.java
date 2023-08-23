package com.example.springjpa;

import com.example.springjpa.service.CustomerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

public class SpringDataSqlServer {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext()) {
            appContext.scan("com.example.springjpa");
            appContext.refresh();

            CustomerService customerService = appContext.getBean(CustomerService.class);
            customerService.crudCustomer();
        }

    }

}
