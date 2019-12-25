package guru.springframework.springbootwebappjava8.service.map;

import guru.springframework.springbootwebappjava8.model.User;
import guru.springframework.springbootwebappjava8.service.UserService;
import guru.springframework.springbootwebappjava8.utility.SecurityUtility;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;


@Service
@Profile("map")
public class UserMapService extends AbstractMapService<User, Long> implements UserService {


    @Override
    public User findByUsername(String userName) {
        Optional returnUser =  map.values().stream().filter(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getUsername().equalsIgnoreCase(userName);
            }
        }).findFirst();

        return (User) returnUser.get();
    }

    @Override
    public Set<User> findAll() {
        return super.findAll();
    }

    @Override
    public User findById(Long id) {
        return super.findById(id);
    }

    @Override
    public User save(User user) {
        if(user.getPassword() == null){

            user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
        }

        return super.save(user);
    }

    @Override
    public void delete(User user) {
        super.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
