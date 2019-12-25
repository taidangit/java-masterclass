package guru.springframework.spring5reactivemongorecipeapp.service;

import guru.springframework.spring5reactivemongorecipeapp.command.RecipeCommand;
import guru.springframework.spring5reactivemongorecipeapp.domain.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {

    Flux<Recipe> getRecipes();

    Mono<Recipe> getRecipe(String id);

    Mono<RecipeCommand> findCommandById(String id);

    Mono<Recipe> saveRecipe(Recipe recipe);

    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command);

    Mono<Void> deleteById(String id);
}
