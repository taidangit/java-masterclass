package guru.springframework.springbootcassandra.service;

import guru.springframework.springbootcassandra.command.ProductCommand;
import guru.springframework.springbootcassandra.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(UUID id);

    Product saveOrUpdateProduct(Product product);

    ProductCommand saveOrUpdateCommand(ProductCommand productCommand);

    void deleteById(UUID id);
}
