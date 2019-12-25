package guru.springframework.springbootactivemq.repository;


import guru.springframework.springbootactivemq.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
