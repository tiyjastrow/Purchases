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
 * Created by joshuakeough on 10/6/16.
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
            File customersfile = new File("/Users/joshuakeough/workspace/Purchases/99c8adfe-customers.csv");
            Scanner fileScanner = new Scanner(customersfile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] columns = line.split(",");
                Customer customer = new Customer(columns[0], columns[1]);
                customers.save(customer);

            }
        }

        if (purchases.count() == 0) {
            File purchasesfile = new File("/Users/joshuakeough/workspace/Purchases/6f448979-purchases.csv");
            Scanner fileScanner = new Scanner(purchasesfile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] columns = line.split(",");
                Customer customer = customers.findById(Integer.parseInt(columns[0]));
                Purchase purchase = new Purchase(columns[1], columns[2], columns[3], columns[4], customer);
                purchases.save(purchase);

            }
        }
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String filter) {
        ArrayList<Purchase> p;
        if (filter != null) {
            p = (ArrayList) purchases.findByCategory(filter);
        }else {
            p= (ArrayList) purchases.findAll();
        }
        model.addAttribute("purchases", p);
        return "home";
    }
}
