package com.emusicstore.service;

import com.emusicstore.entity.Customer;

import java.util.List;

public interface CustomerService {

    void addCustomer (Customer customer);

    Customer getCustomerById (int customerId);

    List<Customer> getCustomers();

    Customer getCustomerByUsername(String username);
}
