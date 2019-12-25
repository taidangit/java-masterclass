package guru.springframework.spring5recipeapp.command;

import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;
    private Set<Recipe> recipes = new HashSet<>();
}
