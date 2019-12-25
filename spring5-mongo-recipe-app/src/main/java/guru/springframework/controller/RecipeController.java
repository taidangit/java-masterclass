package guru.springframework.controller;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String showById(@PathVariable String id, Model model) {

        Recipe recipe = recipeService.findById(id);

        model.addAttribute("recipe", recipe);

        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());

        return "recipe/recipe-form";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipe,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {

            return "recipe/recipe-form";
        }

        RecipeCommand savedRecipeCommand=recipeService.saveRecipeCommand(recipe);

        return "redirect:/recipe/show/" + savedRecipeCommand.getId();
    }

    @GetMapping("/update/{id}")
    public String updateRecipe(@PathVariable String id, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(id);

        model.addAttribute("ingredients", recipeCommand.getIngredients());
        model.addAttribute("recipe", recipeCommand);
        model.addAttribute("recipeId", id);

        return "recipe/recipe-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable String id) {

        //log.debug("Deleting id: " + id);

        recipeService.deleteById(id);
        return "redirect:/recipe/list";
    }

}
