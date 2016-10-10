package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by joe on 06/10/2016.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findById(Integer id);
}
