package guru.springframework.springbootmariadb.service;

import guru.springframework.springbootmariadb.command.ProductCommand;
import guru.springframework.springbootmariadb.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    Product saveOrUpdateProduct(Product product);

    ProductCommand saveOrUpdateCommand(ProductCommand productCommand);

    void deleteById(Long id);
}
