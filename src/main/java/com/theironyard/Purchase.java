package com.theironyard;
import javax.persistence.*;

/**
 * Created by halleyfroeb on 10/6/16.
 */
//customer_id,date,credit_card,cvv,category
@Entity
@Table(name = "purchases")
public class Purchase {
    public Purchase() {
    }

    public Purchase(String date, String creditCard, int cvv, String category, Customer customer) {
        this.date = date;
        this.creditCard = creditCard;
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
    String creditCard;

    @Column(nullable = false)
    int cvv;

    @Column(nullable = false)
    String category;

    @ManyToOne
    Customer customer;


}
