package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by joshuakeough on 10/6/16.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    ArrayList<Purchase> findByCategory(String category);
}
