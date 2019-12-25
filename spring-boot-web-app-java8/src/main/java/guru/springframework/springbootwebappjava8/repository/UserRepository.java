package guru.springframework.springbootwebappjava8.repository;

import guru.springframework.springbootwebappjava8.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
