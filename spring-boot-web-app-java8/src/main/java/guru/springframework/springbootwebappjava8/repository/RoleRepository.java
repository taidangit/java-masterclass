package guru.springframework.springbootwebappjava8.repository;

import guru.springframework.springbootwebappjava8.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
}
