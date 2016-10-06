package com.theironyard;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;


public interface PurchaseRepository extends CrudRepository <Purchase, Integer>{
    ArrayList<Purchase> findByCategory(String category);




}
