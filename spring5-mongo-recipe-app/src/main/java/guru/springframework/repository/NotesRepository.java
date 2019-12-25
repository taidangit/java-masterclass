package guru.springframework.repository;

import guru.springframework.domain.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<Notes, String> {
}
