package com.example;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by BHarris on 3/9/17.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    List<Purchase> findByCategory(String category);
    List<Purchase> findByCustomerId(Integer search);

//    @Query("SELECT Purchase FROM Purchase JOIN Customer ON Purchase.customer_id = Customer.id WHERE name = ?1")
//    List<Purchase> findByName(String search);
}
