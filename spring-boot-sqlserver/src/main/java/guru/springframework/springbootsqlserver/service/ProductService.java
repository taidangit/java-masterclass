package guru.springframework.springbootsqlserver.service;


import guru.springframework.springbootsqlserver.command.ProductCommand;
import guru.springframework.springbootsqlserver.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    Product saveOrUpdateProduct(Product product);

    ProductCommand saveOrUpdateCommand(ProductCommand productCommand);

    void deleteById(Long id);
}
