package guru.springframework.springbootpostgress.repository;


import guru.springframework.springbootpostgress.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
