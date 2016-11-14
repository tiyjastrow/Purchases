package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by EdHall on 10/6/16.
 */
@Controller
public class PurchasesController {
    @Autowired
    PurchaseRepository purchases;

    @Autowired
    CustomerRepository customers;

    @PostConstruct
    public void init() throws FileNotFoundException {
        if (customers.count() == 0) {
            File f = new File("99c8adfe-customers.csv");
            Scanner fileScanner = new Scanner(f);

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] column = line.split(",");
                if (!column[0].equals("name"))
                    customers.save(new Customer(column[0], column[1]));
            }
        }

        if (purchases.count() == 0) {
            File f = new File("6f448979-purchases.csv");
            Scanner fileScanner = new Scanner(f);

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] column = line.split(",");
                if (!column[0].equals("customer_id")) {
                    Customer customer = customers.findById(Integer.parseInt(column[0]));
                    Purchase purchase = new Purchase(customer, column[1], column[2], column[3], column[4]);
                    purchases.save(purchase);
                }
            }
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category) {
        List<Purchase> purchaseList;
        if (category == null) {
            purchaseList = (ArrayList) purchases.findAll();
        }
        else {
            purchaseList = (ArrayList) purchases.findByCategory(category);
        }

        model.addAttribute("purchases", purchaseList);
        return "home";
    }
}
