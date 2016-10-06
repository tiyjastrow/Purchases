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
 * Created by Zach on 10/6/16.
 */

@Controller
public class PurchasesController {

    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;


    @PostConstruct
    public void init() throws FileNotFoundException {
        if(customers.count() == 0 && purchases.count() == 0){
            parseCustomersFile();
            parsePurchasesFile();
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category) {
        List<Purchase> purchaseList;
        if(category != null){
            purchaseList = (ArrayList) purchases.findAllByCategory(category);
        }else {
            purchaseList = (ArrayList) purchases.findAll();
        }

        model.addAttribute("purchases",purchaseList);
        return "home";
    }

    private void parseCustomersFile() throws FileNotFoundException {
        File customerFile = new File("customers.csv");
        Scanner scannerCustomer = new Scanner(customerFile);
        String line;
        while (scannerCustomer.hasNext()) {
            line = scannerCustomer.nextLine();
            while (line.startsWith("name,email")) {
                line = scannerCustomer.nextLine();
            }
            String[] columns = line.split(",");
            Customer customer = new Customer(columns[0], columns[1]);
            customers.save(customer);
        }
    }

    private void parsePurchasesFile() throws FileNotFoundException {
        File purchaseFile = new File("purchases.csv");
        Scanner scannerPurchase = new Scanner(purchaseFile);
        String line;
        while (scannerPurchase.hasNext()) {
            line = scannerPurchase.nextLine();
            while (line.startsWith("customer_id")) {
                line = scannerPurchase.nextLine();
            }
            String[] columns = line.split(",");
            Customer customer = customers.findById(Integer.parseInt(columns[0]));
            Purchase purchase = new Purchase(columns[1], columns[2], columns[3], columns[4], customer);
            purchases.save(purchase);
        }
    }
}