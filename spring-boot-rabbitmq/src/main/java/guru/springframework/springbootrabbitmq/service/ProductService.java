package guru.springframework.springbootrabbitmq.service;


import guru.springframework.springbootrabbitmq.command.ProductCommand;
import guru.springframework.springbootrabbitmq.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    Product saveOrUpdateProduct(Product product);

    ProductCommand saveOrUpdateCommand(ProductCommand productCommand);

    void deleteById(Long id);

    void sendMessage(Long id);
}
