package com.emusicstore.controller;

import com.emusicstore.entity.Cart;
import com.emusicstore.entity.Customer;
import com.emusicstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserDetailsManager userDetailsManager;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private CustomerService customerService;

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("titlePage", "Register Customer");

        return "registerCustomer";
    }

    @PostMapping("/processRegister")
    public String processRegister(@Valid @ModelAttribute("customer") Customer customer,
                                  BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "registerCustomer";
        }

        List<Customer> customers = customerService.getCustomers();

        for (int i=0; i < customers.size(); i++) {
            if(customer.getEmail().equals(customers.get(i).getEmail())) {
                model.addAttribute("emailMsg", "Email already exists.");

                return "registerCustomer";
            }
        }

        String userName = customer.getUsername();

        boolean userExists = doesUserExist(userName);

        if (userExists) {
            model.addAttribute("registrationError", "User name already exists.");
            return "registerCustomer";
        }

        customer.setBillingAddress(customer.getBillingAddress());
        customer.setShippingAddress(customer.getShippingAddress());
        customer.setCart(new Cart());
        customer.setEnabled(true);
        customerService.addCustomer(customer);

        String encodedPassword = passwordEncoder.encode(customer.getPassword());

        encodedPassword = "{bcrypt}" + encodedPassword;

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");

        User tempUser = new User(userName, encodedPassword, authorities);

        userDetailsManager.createUser(tempUser);

        return "registerCustomerSuccess";

    }

    private boolean doesUserExist(String userName) {
        boolean exists = userDetailsManager.userExists(userName);

        return exists;
    }
}
