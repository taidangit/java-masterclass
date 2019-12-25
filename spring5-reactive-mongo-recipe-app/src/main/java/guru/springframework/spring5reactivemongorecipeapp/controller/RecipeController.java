package guru.springframework.spring5reactivemongorecipeapp.controller;

import guru.springframework.spring5reactivemongorecipeapp.command.RecipeCommand;
import guru.springframework.spring5reactivemongorecipeapp.domain.Recipe;
import guru.springframework.spring5reactivemongorecipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    private WebDataBinder webDataBinder;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @InitBinder("recipe")
    public void initBinder(WebDataBinder webDataBinder){
        this.webDataBinder = webDataBinder;
    }

    @GetMapping("/list")
    public String findAll(Model model) {

        model.addAttribute("recipes", recipeService.getRecipes().collectList().block());

        return "recipe/recipes";
    }

    @GetMapping("/show/{id}")
    public String showById(@PathVariable String id, Model model) {

        model.addAttribute("recipe", recipeService.getRecipe(id).block());

        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());

        return "recipe/recipe-form";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipe,
                               BindingResult result, Model model) {
       /* webDataBinder.validate();
        BindingResult result = webDataBinder.getBindingResult();*/

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> log.error(objectError.getDefaultMessage()));

            return "recipe/recipe-form";
        }

        RecipeCommand savedRecipeCommand=recipeService.saveRecipeCommand(recipe).block();

        return "redirect:/recipe/show/" + savedRecipeCommand.getId();
    }

    @GetMapping("/update/{id}")
    public String updateRecipe(@PathVariable String id, Model model) {

        model.addAttribute("recipe",  recipeService.getRecipe(id).block());
        model.addAttribute("recipeId", id);

        return "recipe/recipe-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable String id) {

        recipeService.deleteById(id);
        return "redirect:/recipe/list";
    }

}
