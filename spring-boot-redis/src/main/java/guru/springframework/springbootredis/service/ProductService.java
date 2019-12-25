package guru.springframework.springbootredis.service;

import guru.springframework.springbootredis.command.ProductCommand;
import guru.springframework.springbootredis.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(String id);

    Product saveOrUpdateProduct(Product product);

    ProductCommand saveOrUpdateCommand(ProductCommand productCommand);

    void deleteById(String id);
}
