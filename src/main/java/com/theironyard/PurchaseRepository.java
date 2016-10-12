package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by ericcollier on 10/11/16.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    ArrayList<Purchase> findAllByCategory(String category);
}
