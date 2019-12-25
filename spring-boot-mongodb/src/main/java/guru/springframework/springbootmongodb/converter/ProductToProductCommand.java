package guru.springframework.springbootmongodb.converter;

import guru.springframework.springbootmongodb.command.ProductCommand;
import guru.springframework.springbootmongodb.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductCommand implements Converter<Product, ProductCommand> {
    @Override
    public ProductCommand convert(Product product) {

        ProductCommand productCommand = new ProductCommand();
        productCommand.setId(product.getId().toHexString());
        productCommand.setDescription(product.getDescription());
        productCommand.setPrice(product.getPrice());
        productCommand.setImageUrl(product.getImageUrl());
        return productCommand;
    }
}
