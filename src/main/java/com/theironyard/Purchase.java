package com.theironyard;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String category;

    @Column(nullable = false)
    String creditCard;

    @Column(nullable = false)
    String cvv;

    @Column(nullable = false)
    String date;

    @ManyToOne
    Customer customer;

    public Purchase() {
    }

    public Purchase(String date, String creditCard, String cvv, String category, Customer customer) {

        this.date = date;
        this.creditCard = creditCard;
        this.cvv = cvv;
        this.category = category;
        this.customer = customer;
    }
}
