package guru.springframework.spring5reactivemongorecipeapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Document
public class Ingredient {

    private String id= UUID.randomUUID().toString();

    @NotNull
    @Min(value = 1)
    private BigDecimal amount;

    @NotBlank
    private String description;

    private UnitOfMeasure uom;


    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }
}

