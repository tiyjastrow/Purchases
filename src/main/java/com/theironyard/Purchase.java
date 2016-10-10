package com.theironyard;

import javax.persistence.*;

/**
 * Created by joe on 06/10/2016.
 */
@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String credit_card;

    @Column(nullable = false)
    String cvv;

    @Column(nullable = false)
    String category;

    @ManyToOne
    Customer customer;

    public Purchase() {
    }

    public Purchase(String date, String credit_card, String cvv, String category, Customer customer) {
        this.date = date;
        this.credit_card = credit_card;
        this.cvv = cvv;
        this.category = category;
        this.customer = customer;
    }
}
