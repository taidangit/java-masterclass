package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/list")
    public String findAll(Model model) {

        model.addAttribute("recipes", recipeService.findAll());

        return "recipe/recipes";
    }

    @GetMapping("/show/{id}")
    public String showById(@PathVariable Long id, Model model){

        model.addAttribute("recipe", recipeService.findById(id));

        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipe-form";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand,
                               BindingResult result){
        if(result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.error(objectError.getDefaultMessage());
            });
            return "recipe/recipe-form";
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);


        return "redirect:/recipe/show/" + savedCommand.getId();
    }

    @GetMapping("/update/{id}")
    public String updateRecipe(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return  "recipe/recipe-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){

        log.debug("Deleting id: " + id);

        recipeService.deleteById(id);
        return "redirect:/recipe/list";
    }

}
