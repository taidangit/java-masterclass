package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Document
public class Category {

    private String id= UUID.randomUUID().toString();

    private String description;

    private Set<Recipe> recipes = new HashSet<>();
}
