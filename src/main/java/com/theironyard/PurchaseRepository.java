package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by joe on 06/10/2016.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    ArrayList<Purchase> findAllByCategory(String category);
}
