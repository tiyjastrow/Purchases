package com.theironyard;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by lee on 10/7/16.
 */
@Entity
@Table(name = "purchases")
public class Purchase {
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

    public Purchase() {
    }

    public Purchase(Customer customer, String date, String creditCard, String cvv, String category) {
        this.customer = customer;
        this.date = date;
        this.creditCard = creditCard;
        this.cvv = cvv;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDate() {
        return date;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getCvv() {
        return cvv;
    }

    public String getCategory() {
        return category;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
