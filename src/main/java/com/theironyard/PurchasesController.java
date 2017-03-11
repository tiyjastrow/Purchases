package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    PurchaseRepository purchases;
    @Autowired
    CustomerRepository customers;

    @PostConstruct
    public void init() throws FileNotFoundException {
        if (customers.count() == 0) {
            loadCustomers();
        }
        if (purchases.count() == 0) {
            loadPurchases();
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String categoryLimit, String sortField, String order) {
        List<Purchase> purchaseList;
        if (categoryLimit != null && !categoryLimit.equals("All")) {
            purchaseList = purchases.findByCategory(categoryLimit);
        }
        else if (sortField != null){
            Sort sort;
            if (order != null && !order.isEmpty()) {
                sort = new Sort(Sort.Direction.DESC ,sortField);
            } else {
                sort = new Sort(sortField);
                model.addAttribute("desc", ",desc");
                model.addAttribute(sortField, sortField);
            }
            purchaseList = purchases.findAll(sort);
        }
        else {
            purchaseList = purchases.findAll();
        }
        model.addAttribute("purchases", purchaseList);
        model.addAttribute("categories", purchases.findDistinctCategory());
        return "home";
    }

    private void loadCustomers() throws FileNotFoundException {
        final int NAME = 0;
        final int EMAIL = 1;
        File f = new File("customers.csv");
        Scanner scanner = new Scanner(f);
        scanner.nextLine();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] customerArr = line.split(",");
            customers.save(new Customer(customerArr[NAME], customerArr[EMAIL]));
        }
        scanner.close();
    }

    private void loadPurchases() throws FileNotFoundException {
        final int CUSTOMER_ID = 0;
        final int DATE = 1;
        final int CREDIT_CARD = 2;
        final int CVV = 3;
        final int CATEGORY = 4;

        File f = new File("purchases.csv");
        Scanner scanner = new Scanner(f);
        scanner.nextLine();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] purchaseArr = line.split(",");
            purchases.save(new Purchase(purchaseArr[DATE],
                                        purchaseArr[CREDIT_CARD],
                                        Integer.parseInt(purchaseArr[CVV]),
                                        purchaseArr[CATEGORY],
                                        customers.findOne(Integer.parseInt(purchaseArr[CUSTOMER_ID]))));

        }
        scanner.close();
    }
}
