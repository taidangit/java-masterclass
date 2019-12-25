package guru.springframework.springbootactivemq.service;


import guru.springframework.springbootactivemq.command.ProductCommand;
import guru.springframework.springbootactivemq.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    Product saveOrUpdateProduct(Product product);

    ProductCommand saveOrUpdateCommand(ProductCommand productCommand);

    void deleteById(Long id);

    void sendMessage(Long id);
}
