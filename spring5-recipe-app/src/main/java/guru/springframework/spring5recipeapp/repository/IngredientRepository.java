package guru.springframework.spring5recipeapp.repository;

import guru.springframework.spring5recipeapp.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
