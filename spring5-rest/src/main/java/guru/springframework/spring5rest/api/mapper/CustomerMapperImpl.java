package guru.springframework.spring5rest.api.mapper;

import guru.springframework.spring5rest.api.model.CustomerDTO;
import guru.springframework.spring5rest.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if(customer==null) {
            return null;
        }

        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());

        return customerDTO;
    }

    @Override
    public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if(customerDTO==null) {
            return null;
        }

        Customer customer=new Customer();
        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());

        return customer;
    }
}
