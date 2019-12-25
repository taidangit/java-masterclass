package academy.learnprogramming.bbbms.controller;

import academy.learnprogramming.bbbms.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("demo")
    public String demo(Model model) {
        model.addAttribute("message", demoService.getMessage());
        return "demo/demo";
    }

}

