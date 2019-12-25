package guru.springframework.springbootrabbitmq.repository;


import guru.springframework.springbootrabbitmq.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
