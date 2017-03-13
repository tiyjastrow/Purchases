package com.theironyard;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.OrderBy;
import java.util.List;

/**
 * Created by MacbookStudioPro on 3/9/17.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {

    List<Purchase> findAll();
    List<Purchase> findByCategory(String category);
    List<Purchase> findAll(Sort sort);
}
