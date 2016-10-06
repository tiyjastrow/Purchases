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

@Controller
public class PurchaseTrackerController {
    @Autowired
    CustomerRepository customers;
    @Autowired
    PurchaseRepository purchases;

    @PostConstruct
    public void init() throws FileNotFoundException{
        parseCustomers();
        parsePurchase();
    }

    private void parsePurchase() throws FileNotFoundException {
        File purchaseFile = new File("purchases.csv");
        Scanner purchaseScan = new Scanner(purchaseFile);
        if (purchases.count() == 0){
            while(purchaseScan.hasNext()){
                String line = purchaseScan.nextLine();
                while(line.startsWith("customer_id,date,credit_card,cvv,category")){
                    line = purchaseScan.nextLine();
                }
                String[] columns = line.split(",");
                Purchase purchase = new Purchase(columns[1], columns[2], columns[3], columns[4], customers.findById(Integer.parseInt(columns[0])));
                purchases.save(purchase);
            }
        }
    }

    private void parseCustomers() throws FileNotFoundException {
        File customerFile = new File("customers.csv");
        Scanner customerScan = new Scanner(customerFile);
        if (customers.count() == 0){
            while(customerScan.hasNext()){
                String line = customerScan.nextLine();
                while (line.startsWith("name,email")){
                    line = customerScan.nextLine();
                }
                String[] columns = line.split(",");
                Customer customer = new Customer(columns[0], columns[1]);
                customers.save(customer);
            }
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category){
        List<Purchase> purchaseList;
        if (category != null){
            purchaseList = purchases.findByCategory(category);
        } else {
            purchaseList = (ArrayList) purchases.findAll();
        }
        model.addAttribute("purchases", purchaseList);
        return "home";
    }
}
