package guru.springframework.springbootwebappjava8.service.map;


import guru.springframework.springbootwebappjava8.model.Product;
import guru.springframework.springbootwebappjava8.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("map")
public class ProductMapService extends AbstractMapService<Product, Long> implements ProductService {

    @Autowired
    private ProductService productService;

    @Override
    public Set<Product> findAll() {
        return super.findAll();
    }

    @Override
    public Product findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Product save(Product product) {
        return super.save(product);
    }

    @Override
    public void delete(Product product) {
        super.delete(product);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
