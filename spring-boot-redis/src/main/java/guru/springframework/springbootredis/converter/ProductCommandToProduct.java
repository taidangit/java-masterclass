package guru.springframework.springbootredis.converter;

import guru.springframework.springbootredis.command.ProductCommand;
import guru.springframework.springbootredis.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

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
