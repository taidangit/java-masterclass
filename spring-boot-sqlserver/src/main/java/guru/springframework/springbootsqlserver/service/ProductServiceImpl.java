package guru.springframework.springbootsqlserver.service;

import guru.springframework.springbootsqlserver.command.ProductCommand;
import guru.springframework.springbootsqlserver.converter.ProductCommandToProduct;
import guru.springframework.springbootsqlserver.converter.ProductToProductCommand;
import guru.springframework.springbootsqlserver.domain.Product;
import guru.springframework.springbootsqlserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Product getProduct(Long id) {
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
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


}
