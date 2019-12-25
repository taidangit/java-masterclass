package guru.springframework.springbootwebapp.service;

import guru.springframework.springbootwebapp.model.User;

public interface UserService extends CrudService<User, Long> {

    User findByUsername(String username);
}
