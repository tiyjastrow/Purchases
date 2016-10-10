package com.theironyard;

import javax.persistence.*;

/**
 * Created by EdHall on 10/6/16.
 */
@Entity
@Table(name = "purchases")
public class Purchase {

    public Purchase() {
    }

    public Purchase(Customer customer, String date, String creditCard, String cvv, String category) {
        this.customer = customer;
        this.date = date;
        this.creditCard = creditCard;
        this.cvv = cvv;
        this.category = category;
    }


    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    Customer customer;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String creditCard;

    @Column(nullable = false)
    String cvv;

    @Column(nullable = false)
    String category;

}