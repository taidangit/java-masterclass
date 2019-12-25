package guru.springframework.springbootmysql.repository;

import guru.springframework.springbootmysql.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
