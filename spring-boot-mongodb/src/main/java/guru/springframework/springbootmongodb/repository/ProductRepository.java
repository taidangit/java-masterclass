package guru.springframework.springbootmongodb.repository;

import guru.springframework.springbootmongodb.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
