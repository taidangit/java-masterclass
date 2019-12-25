package com.emusicstore.dao;

import com.emusicstore.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    void addCustomer (Customer customer);

    Customer getCustomerById (int customerId);

    List<Customer> getCustomers();

    Customer getCustomerByUsername (String username);
}
