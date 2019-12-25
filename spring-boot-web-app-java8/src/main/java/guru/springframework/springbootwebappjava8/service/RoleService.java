package guru.springframework.springbootwebappjava8.service;

import guru.springframework.springbootwebappjava8.model.Role;

public interface RoleService  extends CrudService<Role, Long> {
    Role findByRole(String role);
}
