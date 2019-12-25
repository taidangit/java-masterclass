package guru.springframework.springbootwebappjava8.service.springdatajpa;

import guru.springframework.springbootwebappjava8.model.Product;
import guru.springframework.springbootwebappjava8.repository.ProductRepository;
import guru.springframework.springbootwebappjava8.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class ProductSDJpaService implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Set<Product> findAll() {
        Set<Product> products=new HashSet<>();
        productRepository.findAll().forEach(products::add);
        return products;


    }

    @Override
    public Product findById(Long aLong) {
        return productRepository.findById(aLong).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteById(Long aLong) {
        productRepository.deleteById(aLong);
    }
}
