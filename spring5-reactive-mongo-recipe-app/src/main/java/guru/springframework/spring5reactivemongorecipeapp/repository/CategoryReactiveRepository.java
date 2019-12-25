package guru.springframework.spring5reactivemongorecipeapp.repository;

import guru.springframework.spring5reactivemongorecipeapp.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {

    Mono<Category> findByDescription(String description);
}
