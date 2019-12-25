package guru.springframework.springbootcassandra.service;

import guru.springframework.springbootcassandra.command.ProductCommand;
import guru.springframework.springbootcassandra.converter.ProductCommandToProduct;
import guru.springframework.springbootcassandra.converter.ProductToProductCommand;
import guru.springframework.springbootcassandra.domain.Product;
import guru.springframework.springbootcassandra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCommandToProduct productCommandToProduct;

    @Autowired
    private ProductToProductCommand productToProductCommand;

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add); //fun with Java 8
        return products;
    }

    @Override
    public Product getProduct(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public ProductCommand saveOrUpdateCommand(ProductCommand productCommand) {

        Product savedProduct=saveOrUpdateProduct(productCommandToProduct.convert(productCommand));

        return productToProductCommand.convert(savedProduct);
    }

    @Override
    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }


}
