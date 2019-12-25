package guru.springframework.springbootsqlserver.repository;

import guru.springframework.springbootsqlserver.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
