package com.theironyard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by EdHall on 10/6/16.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {

    List<Purchase> findByCategory(String category);
}
