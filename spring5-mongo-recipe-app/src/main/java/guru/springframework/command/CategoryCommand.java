package guru.springframework.command;

import guru.springframework.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CategoryCommand {

    private String id;
    private String description;
    private Set<Recipe> recipes = new HashSet<>();
}
