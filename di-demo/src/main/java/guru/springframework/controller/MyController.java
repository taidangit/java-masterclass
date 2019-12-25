package guru.springframework.controller;

import guru.springframework.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    @Autowired
    private GreetingService greetingService;

    public String hello() {
        System.out.println("Hello Spring Boot!");

        return greetingService.sayGreeting();
    }
}
