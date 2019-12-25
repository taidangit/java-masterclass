package guru.springframework.springbootwebapp.repository;

import guru.springframework.springbootwebapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
}
