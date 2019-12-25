package guru.springframework.springbootdb2.service;

import guru.springframework.springbootdb2.command.ProductCommand;
import guru.springframework.springbootdb2.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    Product saveOrUpdateProduct(Product product);

    ProductCommand saveOrUpdateCommand(ProductCommand productCommand);

    void deleteById(Long id);

}
