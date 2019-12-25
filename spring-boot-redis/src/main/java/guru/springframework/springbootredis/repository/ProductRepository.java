package guru.springframework.springbootredis.repository;

import guru.springframework.springbootredis.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
}
