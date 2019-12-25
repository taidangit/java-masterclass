package guru.springframework.springbootmysql.service;

import guru.springframework.springbootmysql.command.ProductCommand;
import guru.springframework.springbootmysql.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    Product saveOrUpdateProduct(Product product);

    ProductCommand saveOrUpdateCommand(ProductCommand productCommand);

    void deleteById(Long id);
}
