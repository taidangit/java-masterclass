package guru.springframework.controller;

import guru.springframework.command.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.service.IngredientService;
import guru.springframework.service.RecipeService;
import guru.springframework.service.UnitOfMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {

    private RecipeService recipeService;

    private IngredientService ingredientService;

    private UnitOfMeasureService unitOfMeasureService;

    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        //log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        return "recipe/ingredient/ingredients";
    }



    @GetMapping("recipe/{id}/ingredient/new")
    public String newIngredient(@PathVariable("id") String recipeId, Model model) {

        model.addAttribute("recipeId", recipeId);

        //IngredientCommand ingredientCommand = new IngredientCommand();
        Ingredient ingredient=new Ingredient();
        //ingredient.setUom(new UnitOfMeasure());

        model.addAttribute("ingredient", ingredient);

        Recipe recipe=recipeService.findById(recipeId);
        //model.addAttribute("ingredient1", recipe.getIngredients());

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredient-form";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id,
                                       Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable("recipeId") String recipeId,
                                         @PathVariable String id, Model model) {
        model.addAttribute("recipeId", recipeId);

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredient-form";
    }

    @PostMapping("/ingredient/{recipeId}/save")
    public String saveOrUpdate(@ModelAttribute("ingredient") IngredientCommand command,
                               @PathVariable String recipeId) {

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command, recipeId);

        return "redirect:/recipe/" + recipeId + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id) {

        //log.debug("deleting ingredient id:" + id);

        ingredientService.deleteById(recipeId, id);

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
