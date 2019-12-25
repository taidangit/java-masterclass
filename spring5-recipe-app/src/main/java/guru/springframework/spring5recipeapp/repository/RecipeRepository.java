package guru.springframework.spring5recipeapp.repository;

import guru.springframework.spring5recipeapp.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
