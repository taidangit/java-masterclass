package guru.springframework.spring5reactivemongorecipeapp.service;

import guru.springframework.spring5reactivemongorecipeapp.domain.Ingredient;
import guru.springframework.spring5reactivemongorecipeapp.domain.Recipe;
import guru.springframework.spring5reactivemongorecipeapp.repository.RecipeReactiveRepository;
import guru.springframework.spring5reactivemongorecipeapp.repository.UnitOfMeasureReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private RecipeReactiveRepository recipeRepository;
    private UnitOfMeasureReactiveRepository unitOfMeasureRepository;

    @Autowired
    public IngredientServiceImpl(RecipeReactiveRepository recipeRepository,
                                 UnitOfMeasureReactiveRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Mono<Ingredient> findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {

        Recipe recipe = recipeRepository.findById(recipeId).block();

        if (recipe == null) {
            log.error("recipe id not found. Id: " + recipeId);
            return Mono.empty();
        }

        Optional<Ingredient> ingredientOptional =
                recipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getId().equals(ingredientId))
                        .findFirst();

        if (!ingredientOptional.isPresent()) {
            log.error("Ingredient id not found: " + ingredientId);
        }

        return Mono.just(ingredientOptional.get());
    }


    @Override
    public Mono<Ingredient> saveIngredient(Ingredient ingredient, String recipeId) {

        Recipe recipe = recipeRepository.findById(recipeId).block();

        if (recipe == null) {
            log.error("Recipe not found for id: " + recipeId);
            return Mono.empty();
        } else {

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient1 -> ingredient.getId().equals(ingredient.getId()))
                    .findFirst();
            if (!ingredientOptional.isPresent()) {
                //Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                //ingredientRepository.save(ingredient);
                recipe.getIngredients().add(ingredient);

            } else {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredient.getDescription());
                ingredientFound.setAmount(ingredient.getAmount());

                ingredientFound.setUom(unitOfMeasureRepository.findByDescription(
                        ingredient.getUom().getDescription()).block());
                //ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUom().getId()).get());


                recipe.getIngredients().add(ingredientFound);
            }

            Recipe savedRecipe=recipeRepository.save(recipe).block();

            Optional<Ingredient> savedIngredientOptional =
                    savedRecipe.getIngredients().stream()
                            .filter(recipeIngredients -> recipeIngredients.getDescription().equals(
                                    ingredient.getDescription()))
                            .filter(recipeIngredients -> recipeIngredients.getAmount().equals(
                                    ingredient.getAmount()))
                            .filter(recipeIngredients -> recipeIngredients.getUom().getDescription().equals(
                                    ingredient.getUom().getDescription()))
                            .findFirst();

            return Mono.just(savedIngredientOptional.get());
        }
    }

    @Override
    public Mono<Void> deleteById(String recipeId, String idToDelete) {

        Recipe recipe = recipeRepository.findById(recipeId).block();

        if (recipe != null) {

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(idToDelete))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                //Ingredient ingredientToDelete = ingredientOptional.get();

                recipe.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe).block();
            }
        } else {
            log.debug("Recipe Id Not found. Id:" + recipeId);
        }


        return Mono.empty();
    }
}
