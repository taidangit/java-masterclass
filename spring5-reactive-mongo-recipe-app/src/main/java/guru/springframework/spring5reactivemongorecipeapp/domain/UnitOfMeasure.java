package guru.springframework.spring5reactivemongorecipeapp.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document
public class UnitOfMeasure {

    private String id= UUID.randomUUID().toString();

    private String description;
}
