package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jeremypitt on 10/6/16.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findById(Integer id);
}
