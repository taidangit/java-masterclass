package guru.springframework.springmvcrest.service;

import guru.springframework.springmvcrest.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomer();

    Customer findCustomerById(Long id);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Long id);
}
