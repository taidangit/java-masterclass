package guru.springframework.springbootwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    String index(){
        return "index";
    }

    @GetMapping("/access-denied")
    public String notAuth(){
        return "access-denied";
    }
}
