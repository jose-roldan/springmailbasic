package com.example.springjpahib;


import com.example.springjpahib.service.CustomerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.example.springjpahib")
public class SpringDataSqlServerHib {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(SpringDataSqlServerHib.class);

        CustomerService customerService = appContext.getBean(CustomerService.class);
        customerService.crudCustomer();

    }

}
