package guru.springframework.springbootpostgress.converter;


import guru.springframework.springbootpostgress.command.ProductCommand;
import guru.springframework.springbootpostgress.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductCommandToProduct implements Converter<ProductCommand, Product> {


    @Override
    public Product convert(ProductCommand productCommand) {

        Product product = new Product();
        if (productCommand.getId() != null) {
            product.setId(productCommand.getId());
        }

        product.setDescription(productCommand.getDescription());
        product.setPrice(productCommand.getPrice());
        product.setImageUrl(productCommand.getImageUrl());
        return product;
    }
}
