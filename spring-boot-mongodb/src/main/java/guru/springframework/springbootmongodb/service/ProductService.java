package guru.springframework.springbootmongodb.service;

import guru.springframework.springbootmongodb.command.ProductCommand;
import guru.springframework.springbootmongodb.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(String id);

    Product saveOrUpdateProduct(Product product);

    ProductCommand saveOrUpdateCommand(ProductCommand productCommand);

    void deleteById(String id);
}
