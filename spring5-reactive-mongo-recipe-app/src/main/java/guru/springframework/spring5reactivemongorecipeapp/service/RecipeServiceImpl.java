package guru.springframework.spring5reactivemongorecipeapp.service;

import guru.springframework.spring5reactivemongorecipeapp.command.RecipeCommand;
import guru.springframework.spring5reactivemongorecipeapp.converter.RecipeCommandToRecipe;
import guru.springframework.spring5reactivemongorecipeapp.converter.RecipeToRecipeCommand;
import guru.springframework.spring5reactivemongorecipeapp.domain.Recipe;
import guru.springframework.spring5reactivemongorecipeapp.exception.NotFoundException;
import guru.springframework.spring5reactivemongorecipeapp.repository.RecipeReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeReactiveRepository recipeRepository;
    private RecipeCommandToRecipe recipeCommandToRecipe;
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    public RecipeServiceImpl(RecipeReactiveRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Flux<Recipe> getRecipes() {

       return recipeRepository.findAll();
    }

    @Override
    public Mono<Recipe> getRecipe(String id) {

        Recipe recipe=recipeRepository.findById(id).block();
        if(recipe==null) {
            throw new NotFoundException("Recipe Not Found. For ID value: "+id);
        }

        return recipeRepository.findById(id);
    }

    @Override
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
        return recipeRepository.save(recipeCommandToRecipe.convert(command))
                .map(recipeToRecipeCommand::convert);
    }

    @Override
    public Mono<Recipe> saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Mono<RecipeCommand> findCommandById(String id) {
        return recipeRepository.findById(id)
                .map(recipeToRecipeCommand::convert);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        recipeRepository.deleteById(id).block();
        return Mono.empty();
    }
}
