package com.iqmsoft.boot.vaadin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqmsoft.boot.vaadin.domain.Customer;
import com.iqmsoft.boot.vaadin.repos.CustomerRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Service
public class CustomerService {

    private static final Logger LOGGER = Logger.getLogger(CustomerService.class.getName());

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public synchronized List<Customer> findAll() {
        return stream(customerRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public synchronized List<Customer> findAll(String stringFilter) {
        return stream(customerRepository.findNamesLike(stringFilter.toUpperCase()).spliterator(), false).collect(Collectors.toList());
    }

    public synchronized void delete(Customer value) {
        customerRepository.delete(value);
    }

    public synchronized void save(Customer entry) {
        if (entry == null) {
            LOGGER.log(Level.SEVERE,
                    "Customer is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
            return;
        }
        customerRepository.save(entry);
    }

  

}