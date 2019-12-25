package guru.springframework.springbootmongodb.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Document
public class Product {

    @Id
    private ObjectId id;
    private String description;
    private BigDecimal price;
    private String imageUrl;

}

