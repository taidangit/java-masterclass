package guru.springframework.springdatarest.repository;

import guru.springframework.springdatarest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
