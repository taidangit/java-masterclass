package guru.springframework.springbootneo4j.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.math.BigDecimal;

@Getter
@Setter
@NodeEntity
public class Product {

    @GraphId
    private Long id;

    private String description;

    private BigDecimal price;

    private String imageUrl;

}

