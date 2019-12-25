package guru.springframework.springbootneo4j.repository;


import guru.springframework.springbootneo4j.domain.Product;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProductRepository extends Neo4jRepository<Product, Long> {
}
