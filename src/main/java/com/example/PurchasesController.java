package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

@Controller
public class PurchasesController {
    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;

    @PostConstruct
    public void init() throws FileNotFoundException {
        if(customers.count() == 0) {
            File f = null;
            try {
                f = new File("99c8adfe-customers.csv");
            } catch (Exception e) {
                e.printStackTrace();
            }

            Scanner customer = new Scanner(f);

            while (customer.hasNext()) {
                String line = customer.nextLine();
                String[] c = line.split(",");
                if (c[0].equals("name")) continue;

                Customer cust = new Customer(c[0], c[1]);
                customers.save(cust);
            }
        }
        if (purchases.count()==0) {
            File y = null;
            try {
                y = new File("6f448979-purchases.csv");
            } catch (Exception e) {
                e.printStackTrace();
            }

            Scanner purch = new Scanner(y);

            while (purch.hasNext()) {
                String line = purch.nextLine();
                String[] p = line.split(",");
                //customer_id,date,credit_card,cvv,category
                if (p[0].equals("customer_id")) continue;
                Purchase purchase = new Purchase(p[1], p[2], Integer.parseInt(p[3]),p[4], customers.findOne(Integer.parseInt(p[0])));
                purchases.save(purchase);
            }
        }



    }


    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String home(Model model, String category){
        List<Purchase> purchList;
        if(category == null){
            purchList = (List)purchases.findAll();
        } else {
            purchList = purchases.findByCategory(category);
        }
        model.addAttribute("purchases", purchList );
        return "home";
    }


}
