package guru.springframework.spring5reactivemongorecipeapp.repository;

import guru.springframework.spring5reactivemongorecipeapp.domain.Ingredient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IngredientReactiveRepository extends ReactiveMongoRepository<Ingredient, String> {

}
