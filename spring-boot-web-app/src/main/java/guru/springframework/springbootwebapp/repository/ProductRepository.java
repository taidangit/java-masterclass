package guru.springframework.springbootwebapp.repository;

import guru.springframework.springbootwebapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
