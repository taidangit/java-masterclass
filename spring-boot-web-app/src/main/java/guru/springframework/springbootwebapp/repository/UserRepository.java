package guru.springframework.springbootwebapp.repository;

import guru.springframework.springbootwebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
