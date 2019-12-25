package guru.springframework.springbootcassandra.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductCommand {

    private UUID id;

    @NotBlank
    private String description;

    @NotNull
    @Min(value = 1)
    private BigDecimal price;

    @NotBlank
    private String imageUrl;

}
