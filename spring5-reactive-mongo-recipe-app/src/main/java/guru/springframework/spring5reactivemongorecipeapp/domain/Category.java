package guru.springframework.spring5reactivemongorecipeapp.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter
@Setter
@Document
public class Category {

    private String id= UUID.randomUUID().toString();

    private String description;

    private Set<Recipe> recipes=new HashSet<>();

}
