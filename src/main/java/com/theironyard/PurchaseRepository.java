package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Zach on 10/6/16.
 */
public interface PurchaseRepository extends CrudRepository<Purchase,Integer> {


    List<Purchase> findAllByCategory(String category);
}
