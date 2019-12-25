package guru.springframework.springbootwebapp.bootstrap;

import guru.springframework.springbootwebapp.model.Product;
import guru.springframework.springbootwebapp.model.Role;
import guru.springframework.springbootwebapp.model.User;
import guru.springframework.springbootwebapp.service.ProductService;
import guru.springframework.springbootwebapp.service.RoleService;
import guru.springframework.springbootwebapp.service.UserService;
import guru.springframework.springbootwebapp.utility.SecurityUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Slf4j
@Component
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        if (productService.findAll().size() == 0) {
            loadProducts();
            loadUsers();
            loadRoles();
            assignUsersToUserRole();
            assignUsersToAdminRole();
        }

    }

    private void loadProducts() {
        Product shirt = new Product();
        shirt.setDescription("Spring Framework Guru Shirt");
        shirt.setPrice(new BigDecimal("18.95"));
        shirt.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
        productService.save(shirt);

        log.info("Saved Shirt - id: " + shirt.getId());

        Product mug = new Product();
        mug.setDescription("Spring Framework Guru Mug");
        mug.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug.setPrice(new BigDecimal("11.95"));

        productService.save(mug);

        log.info("Saved Mug - id:" + mug.getId());
    }

    private void loadUsers() {
        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("user"));

        userService.save(user1);

        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("admin"));

        userService.save(user2);

    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("ROLE_USER");
        roleService.save(role);
        log.info("Saved role" + role.getRole());

        Role adminRole = new Role();
        adminRole.setRole("ROLE_ADMIN");
        roleService.save(adminRole);
        log.info("Saved role" + adminRole.getRole());
    }

    private void assignUsersToUserRole() {
        Set<Role> roles = roleService.findAll();
        Set<User> users = userService.findAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ROLE_USER")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("user")) {
                        user.addRole(role);
                        userService.save(user);
                    }
                });
            }
        });
    }

    private void assignUsersToAdminRole() {
        Set<Role> roles = roleService.findAll();
        Set<User> users = userService.findAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("admin")) {
                        user.addRole(role);
                        userService.save(user);
                    }
                });
            }
        });
    }
}
