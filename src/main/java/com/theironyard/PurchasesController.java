package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Controller
public class PurchasesController {

    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;

    @PostConstruct
    public void loadCustomerAndPurchaseFiles() throws FileNotFoundException {
        if (customers.count() == 0) {
            try {
                File f = new File("customers.csv");
                Scanner scanner = new Scanner(f);
                scanner.nextLine();
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] columns = line.split(",");
                    Customer customerInfo = new Customer(columns[0], columns[1]);
                    customers.save(customerInfo);
                }
            } catch (FileNotFoundException e){
                System.out.println("Customer file not found exception: "+ e.getMessage());
            }
        }

        if (purchases.count() == 0) {
            try {
                File f = new File("purchases.csv");
                Scanner scanner = new Scanner(f);
                scanner.nextLine();
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] columns = line.split(",");
                    Purchase purchaseInfo = new Purchase(columns[1], columns[2], columns[3], columns[4], customers.findOne(Integer.valueOf(columns[0])));
                    purchases.save(purchaseInfo);
                }
            } catch (FileNotFoundException e){
                System.out.println("Purchase file not found exception: "+ e.getMessage());
            }
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category, Sort sort) {
        List<Purchase> purchaseList;

        if (category != null) {
            purchaseList = purchases.findByCategory(category);
        }

        else if (sort != null){


            purchaseList = purchases.findAll(sort);
        }

        else {
            purchaseList = purchases.findAll();
        }

        model.addAttribute("purchases", purchaseList);
        return "home";
    }

}
