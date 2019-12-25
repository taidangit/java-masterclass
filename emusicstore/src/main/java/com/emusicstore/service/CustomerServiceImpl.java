package com.emusicstore.service;

import com.emusicstore.dao.CustomerDAO;
import com.emusicstore.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return customerDAO.getCustomerByUsername(username);
    }
}
