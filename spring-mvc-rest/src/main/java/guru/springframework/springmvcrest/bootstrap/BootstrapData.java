package guru.springframework.springmvcrest.bootstrap;

import guru.springframework.springmvcrest.domain.Customer;
import guru.springframework.springmvcrest.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BootstrapData implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCustomers();
    }

    private void loadCustomers() {
        Customer customer1=new Customer();
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");
        customerRepository.save(customer1);

        Customer customer2=new Customer();
        customer2.setFirstName("Fiona");
        customer2.setLastName("Glennan");
        customerRepository.save(customer2);

        log.info("Customer saved: "+customerRepository.count());

    }
}
