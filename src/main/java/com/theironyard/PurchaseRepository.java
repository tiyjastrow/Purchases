package com.theironyard;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    List<Purchase> findByCategory(String category);
    List<Purchase> findByCategory(String category, Sort sort);
    @Override
    List<Purchase> findAll();

    @Query("SELECT DISTINCT category FROM Purchase")
    List<String> findDistinctCategory();

    List<Purchase> findAll(Sort sort);
}
