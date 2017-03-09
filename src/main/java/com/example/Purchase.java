package com.example;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    int customer_id;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String credit_card;

    @Column(nullable = false)
    int cvv;

    @Column(nullable = false)
    int category;

    @ManyToOne
    Customer customer;


    public Purchase(int customer_id, String date, String credit_card, int cvv, int category, Customer customer) {
        this.customer_id = customer_id;
        this.date = date;
        this.credit_card = credit_card;
        this.cvv = cvv;
        this.category = category;
        this.customer = customer;
    }
}
