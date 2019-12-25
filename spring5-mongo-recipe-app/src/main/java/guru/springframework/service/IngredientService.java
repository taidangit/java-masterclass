package guru.springframework.service;

import guru.springframework.command.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command, String recipeId);

    void deleteById(String recipeId, String idToDelete);
}
