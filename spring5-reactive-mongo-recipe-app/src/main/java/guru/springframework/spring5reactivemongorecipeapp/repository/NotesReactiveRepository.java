package guru.springframework.spring5reactivemongorecipeapp.repository;

import guru.springframework.spring5reactivemongorecipeapp.domain.Notes;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface NotesReactiveRepository extends ReactiveMongoRepository<Notes, String> {
}
