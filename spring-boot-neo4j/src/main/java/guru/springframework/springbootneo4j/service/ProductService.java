package guru.springframework.springbootneo4j.service;

import guru.springframework.springbootneo4j.command.ProductCommand;
import guru.springframework.springbootneo4j.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    Product saveOrUpdateProduct(Product product);

    ProductCommand saveOrUpdateCommand(ProductCommand productCommand);

    void deleteById(Long id);
}
