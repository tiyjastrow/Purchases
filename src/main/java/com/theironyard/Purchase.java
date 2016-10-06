package com.theironyard;

import javax.persistence.*;

/**
 * Created by jeremypitt on 10/6/16.
 */
@Entity
@Table(name = "purchases")
public class Purchase {
    public Purchase(){}

    public Purchase(String date, String creditcard, String cvv, String category, Customer customer) {
        this.date = date;
        this.creditcard = creditcard;
        this.cvv = cvv;
        this.category = category;
        this.customer = customer;
    }

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String creditcard;

    @Column(nullable = false)
    String cvv;

    @Column(nullable = false)
    String category;

    @ManyToOne
    Customer customer;
}
