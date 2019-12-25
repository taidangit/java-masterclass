package guru.springframework.springbootcassandra.converter;

import guru.springframework.springbootcassandra.command.ProductCommand;
import guru.springframework.springbootcassandra.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ProductCommandToProduct implements Converter<ProductCommand, Product> {


    @Override
    public Product convert(ProductCommand productCommand) {

        Product product = new Product();
        if (productCommand.getId() != null && !StringUtils.isEmpty(productCommand.getId())) {
            product.setId(productCommand.getId());
        }

        product.setDescription(productCommand.getDescription());
        product.setPrice(productCommand.getPrice());
        product.setImageUrl(productCommand.getImageUrl());
        return product;
    }
}
