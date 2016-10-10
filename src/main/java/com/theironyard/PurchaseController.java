package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by joe on 06/10/2016.
 */
@Controller
public class PurchaseController {

    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;

    @PostConstruct
    public void init() throws FileNotFoundException {

        if (customers.count() == 0) {
            readCustomerFile();
        }
        if (purchases.count() == 0) {
            readPurchaseFile();
        }
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, String category) {

        ArrayList<Purchase> purchaseList;

        if (category != null) {
            purchaseList = (ArrayList) purchases.findAllByCategory(category);
        } else {
            purchaseList = (ArrayList<Purchase>) purchases.findAll();
        }

        model.addAttribute("purchases", purchaseList);

        return "home";
    }

    public void readCustomerFile() throws FileNotFoundException {
        File f = new File("customers.csv");
        Scanner fileScanner = new Scanner(f);

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split(",");
            Customer customer = new Customer();
            customer.name = columns[0];
            customer.email = columns[1];
            customers.save(customer);
        }
    }

    public void readPurchaseFile() throws FileNotFoundException {
        File f = new File("purchases.csv");
        Scanner fileScanner = new Scanner(f);

        String line = fileScanner.nextLine();

        while (fileScanner.hasNext()) {
            line = fileScanner.nextLine();
            String[] columns = line.split(",");
            Customer customer = customers.findById(Integer.parseInt(columns[0]));
            Purchase purchase = new Purchase(columns[1], columns[2], columns[3], columns[4], customer);

            purchases.save(purchase);
        }
    }
}
