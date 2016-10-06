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
 * Created by halleyfroeb on 10/6/16.
 */
//customer_id,date,credit_card,cvv,category
//name,email

@Controller
public class PurchasesController {
    @Autowired
    CustomerRepository customers;
    @Autowired
    PurchaseRepository purchases;


    @PostConstruct
    public void init() throws FileNotFoundException {
        if (!customers.exists(1)) {
            File c = new File("99c8adfe-customers.csv");
            Scanner cScanner = new Scanner(c);
            cScanner.nextLine(); // skips first line of file
            while (cScanner.hasNext()) {
                String line = cScanner.nextLine();
                String[] columns = line.split("\\,");
                Customer customer = new Customer(columns[0], columns[1]);
                customers.save(customer);
            }
            cScanner.close();
        }
        if (!purchases.exists(1)) {
            File p = new File("6f448979-purchases.csv");
            Scanner pScanner = new Scanner(p);
            pScanner.nextLine(); // skips first line of file
            while (pScanner.hasNext()) {
                String line = pScanner.nextLine();
                String[] columns = line.split("\\,");
                Purchase purchase = new Purchase(columns[1], columns[2], Integer.parseInt(columns[3]), columns[4],
                        customers.findOne(Integer.parseInt(columns[0])));
                purchases.save(purchase);
            }
            pScanner.close();
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category) {
        List<Purchase> purchaseList;
        if (category != null){
            purchaseList = (ArrayList) purchases.findByCategory(category);
        }
        else {
            purchaseList = (ArrayList) purchases.findAll();
        }
        List<Customer> customerList;
        customerList = (ArrayList) customers.findAll();
        model.addAttribute("purchases", purchaseList);
        return "home";
    }
}
