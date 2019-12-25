package guru.springframework.springbootmariadb.repository;

import guru.springframework.springbootmariadb.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
