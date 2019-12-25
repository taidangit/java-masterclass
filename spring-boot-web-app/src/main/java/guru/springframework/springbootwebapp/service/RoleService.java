package guru.springframework.springbootwebapp.service;

import guru.springframework.springbootwebapp.model.Role;

public interface RoleService  extends CrudService<Role, Long> {
    Role findByRole(String role);
}
