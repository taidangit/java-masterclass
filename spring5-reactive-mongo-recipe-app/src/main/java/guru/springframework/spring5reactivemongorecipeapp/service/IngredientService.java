package guru.springframework.spring5reactivemongorecipeapp.service;

import guru.springframework.spring5reactivemongorecipeapp.domain.Ingredient;
import reactor.core.publisher.Mono;

public interface IngredientService {

    Mono<Ingredient> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<Ingredient> saveIngredient(Ingredient ingredient, String recipeId);

    Mono<Void> deleteById(String recipeId, String idToDelete);
}
