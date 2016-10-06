package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jakefroeb on 10/6/16.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
}
