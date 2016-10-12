package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by ericcollier on 10/11/16.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findById(Integer id);
}
