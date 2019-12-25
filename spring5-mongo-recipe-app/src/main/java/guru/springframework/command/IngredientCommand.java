package guru.springframework.command;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class IngredientCommand {

    private String id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;

}