package guru.springframework.springbootredis.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductCommand {

    private String id;

    @NotBlank
    private String description;

    @NotNull
    @Min(value = 1)
    private BigDecimal price;

    @NotBlank
    private String imageUrl;

}
