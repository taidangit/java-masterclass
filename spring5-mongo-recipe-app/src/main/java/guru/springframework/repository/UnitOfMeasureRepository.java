package guru.springframework.repository;

import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends MongoRepository<UnitOfMeasure, String> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
