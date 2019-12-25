package guru.springframework.springbootpostgress.service;

import guru.springframework.springbootpostgress.command.ProductCommand;
import guru.springframework.springbootpostgress.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    Product saveOrUpdateProduct(Product product);

    ProductCommand saveOrUpdateCommand(ProductCommand productCommand);

    void deleteById(Long id);
}
