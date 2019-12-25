package guru.springframework.springmvcrest.controller;

import guru.springframework.springmvcrest.domain.Customer;
import guru.springframework.springmvcrest.exception.CustomerNotFoundException;
import guru.springframework.springmvcrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CustomerRestController.BASE_URL)
public class CustomerRestController {

    public static final String BASE_URL = "/api/customers";

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomer();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }
        return customer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        customer = customerService.findCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        customer.setId(customerId);
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        customerService.deleteCustomer(customerId);
    }
}
