package com.emusicstore.controller;

import com.emusicstore.entity.Customer;
import com.emusicstore.entity.Product;
import com.emusicstore.service.CustomerService;
import com.emusicstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String admin() {
        return "admin";
    }

    @GetMapping("/productInventory")
    public  String productInventory(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);

        return "productInventory";
    }

    @GetMapping("/customer")
    public String customerManagement(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);

        return "customerManagement";
    }
}
