package guru.springframework.service;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> findAll();

    Recipe findById(String id);

    RecipeCommand findCommandById(String id);

    Recipe saveRecipe(Recipe recipe);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(String id);
}
