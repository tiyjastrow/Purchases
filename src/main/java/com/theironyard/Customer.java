package com.theironyard;

import javax.persistence.*;

/**
 * Created by jeremypitt on 10/6/16.
 */
@Entity
@Table(name = "customers")
public class Customer {

    public Customer(){}

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String email;
}
