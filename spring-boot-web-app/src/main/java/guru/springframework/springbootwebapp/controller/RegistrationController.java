package guru.springframework.springbootwebapp.controller;

import guru.springframework.springbootwebapp.model.Role;
import guru.springframework.springbootwebapp.model.User;
import guru.springframework.springbootwebapp.service.RoleService;
import guru.springframework.springbootwebapp.service.UserService;
import guru.springframework.springbootwebapp.utility.SecurityUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    @Qualifier("userDetailServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model model) {

        model.addAttribute("user", new User());

        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("user") User user,
                                          BindingResult result, Model model) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> log.error(objectError.getDefaultMessage()));
            return "registration-form";
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("registrationError", "User name already exists.");
            log.warn("User name already exists.");
            return "registration-form";
        }

        String encodedPassword = SecurityUtility.passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByRole("ROLE_USER"));

        user.setRoles(roles);

        // save user in the database
        userService.save(user);

        log.info("Successfully created user: " + user.getUsername());

        return "registration-confirmation";
    }

}
