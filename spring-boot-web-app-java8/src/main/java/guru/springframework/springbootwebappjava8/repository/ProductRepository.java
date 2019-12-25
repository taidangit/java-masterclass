package guru.springframework.springbootwebappjava8.repository;

import guru.springframework.springbootwebappjava8.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
