package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
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
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {
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
    }
}
