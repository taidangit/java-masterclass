package guru.springframework.springbootwebappjava8.service;

import guru.springframework.springbootwebappjava8.model.User;

public interface UserService extends CrudService<User, Long> {

    User findByUsername(String username);
}
