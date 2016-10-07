package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by lee on 10/7/16.
 */
public interface CustomerRepository extends CrudRepository <Customer, Integer> {
    Customer findById(Integer id);
}
