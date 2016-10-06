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
import java.util.List;
import java.util.Scanner;

/**
 * Created by jeremypitt on 10/6/16.
 */
@Controller
public class PurchasesController {
    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;

    @PostConstruct
    public void init() throws FileNotFoundException {
        parseCustomers();

        parsePurchases();
    }

    private void parsePurchases() throws FileNotFoundException {
        if (purchases.count() == 0) {
            File f = new File("6f448979-purchases.csv");
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

    private void parseCustomers() throws FileNotFoundException {
        if (customers.count() == 0) {
            File f = new File("99c8adfe-customers.csv");
            Scanner fileScanner = new Scanner(f);
            String line = fileScanner.nextLine();

            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine();
                String[] columns = line.split(",");
                Customer customer = new Customer(columns[0], columns[1]);
                customers.save(customer);
            }
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, String category) {
        List<Purchase> purchaseList;
        if (category != null) {
            purchaseList = (ArrayList) purchases.findByCategory(category);
        } else {
            purchaseList = (ArrayList) purchases.findAll();
        }
        model.addAttribute("purchases", purchaseList);
        return "home";
    }
}
