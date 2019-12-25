package guru.springframework.spring5rest.repository;

import guru.springframework.spring5rest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
