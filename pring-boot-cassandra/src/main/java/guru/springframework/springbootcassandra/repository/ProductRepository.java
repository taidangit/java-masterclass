package guru.springframework.springbootcassandra.repository;


import guru.springframework.springbootcassandra.domain.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ProductRepository extends CassandraRepository<Product, UUID> {
}
