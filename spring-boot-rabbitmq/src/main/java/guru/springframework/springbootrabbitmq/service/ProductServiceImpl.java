package guru.springframework.springbootrabbitmq.service;

import guru.springframework.springbootrabbitmq.SpringBootRabbitmqApplication;
import guru.springframework.springbootrabbitmq.command.ProductCommand;
import guru.springframework.springbootrabbitmq.converter.ProductCommandToProduct;
import guru.springframework.springbootrabbitmq.converter.ProductToProductCommand;
import guru.springframework.springbootrabbitmq.domain.Product;
import guru.springframework.springbootrabbitmq.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCommandToProduct productCommandToProduct;

    @Autowired
    private ProductToProductCommand productToProductCommand;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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

    @Override
    public void sendMessage(Long id) {
        Map<String, Long> actionmap = new HashMap<>();
        actionmap.put("id", id);
        log.info("Sending the index request through queue message");
        rabbitTemplate.convertAndSend(SpringBootRabbitmqApplication.SFG_MESSAGE_QUEUE, actionmap);
    }
}
