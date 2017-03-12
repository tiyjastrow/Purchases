package com.example;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by BHarris on 3/9/17.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
    Customer findByNameContainingIgnoreCase(String search);
}
