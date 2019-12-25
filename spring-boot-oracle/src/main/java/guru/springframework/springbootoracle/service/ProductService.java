package guru.springframework.springbootoracle.service;


import guru.springframework.springbootoracle.command.ProductCommand;
import guru.springframework.springbootoracle.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    Product saveOrUpdateProduct(Product product);

    ProductCommand saveOrUpdateCommand(ProductCommand productCommand);

    void deleteById(Long id);
}
