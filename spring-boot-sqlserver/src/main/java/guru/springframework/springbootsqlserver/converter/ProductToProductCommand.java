package guru.springframework.springbootsqlserver.converter;


import guru.springframework.springbootsqlserver.command.ProductCommand;
import guru.springframework.springbootsqlserver.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductCommand implements Converter<Product, ProductCommand> {
    @Override
    public ProductCommand convert(Product product) {

        ProductCommand productCommand = new ProductCommand();
        productCommand.setId(product.getId());
        productCommand.setDescription(product.getDescription());
        productCommand.setPrice(product.getPrice());
        productCommand.setImageUrl(product.getImageUrl());
        return productCommand;
    }
}
