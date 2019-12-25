package guru.springframework.springbootoracle.repository;


import guru.springframework.springbootoracle.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
