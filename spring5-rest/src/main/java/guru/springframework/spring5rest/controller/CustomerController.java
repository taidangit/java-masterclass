package guru.springframework.spring5rest.controller;

import guru.springframework.spring5rest.api.model.CustomerDTO;
import guru.springframework.spring5rest.api.model.CustomerListDTO;
import guru.springframework.spring5rest.exception.ResourceNotFoundException;
import guru.springframework.spring5rest.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is my Customer Controller")
@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/customers";

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "This will get a list of customers.", notes = "These are some notes about the API.")
    @GetMapping
    public ResponseEntity<CustomerListDTO> getListOfCustomers() {

        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getAllCustomers()),
                HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {

        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<CustomerDTO>(customerService.saveCustomer(customerDTO),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id,
                                                      @RequestBody CustomerDTO customerDTO) {

        CustomerDTO customer = customerService.getCustomerById(id);
        if (customer == null) {
            throw new ResourceNotFoundException();
        }

        return new ResponseEntity<CustomerDTO>(
                customerService.updateCustomer(id, customerDTO),
                HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id,
                                                     @RequestBody CustomerDTO customerDTO) {

        return new ResponseEntity<CustomerDTO>(
                customerService.patchCustomer(id, customerDTO),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        CustomerDTO customer = customerService.getCustomerById(id);
        if (customer == null) {
            throw new ResourceNotFoundException();
        }

        customerService.deleteCustomerById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
