package guru.springframework.springbootwebappjava8.service.springdatajpa;

import guru.springframework.springbootwebappjava8.model.User;
import guru.springframework.springbootwebappjava8.repository.UserRepository;
import guru.springframework.springbootwebappjava8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class UserSDJpaService implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Set<User> findAll() {
        Set<User> users=new HashSet<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User findById(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
