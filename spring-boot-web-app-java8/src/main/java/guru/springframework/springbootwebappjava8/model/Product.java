package guru.springframework.springbootwebappjava8.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends BaseEntity {


    @Version
    @Column(name = "version")
    private Integer version;

    @NotBlank
    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

}
