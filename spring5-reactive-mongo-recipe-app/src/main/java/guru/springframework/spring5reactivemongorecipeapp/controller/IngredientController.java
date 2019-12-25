package guru.springframework.spring5reactivemongorecipeapp.controller;

import guru.springframework.spring5reactivemongorecipeapp.domain.Ingredient;
import guru.springframework.spring5reactivemongorecipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5reactivemongorecipeapp.service.IngredientService;
import guru.springframework.spring5reactivemongorecipeapp.service.RecipeService;
import guru.springframework.spring5reactivemongorecipeapp.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;

    private IngredientService ingredientService;

    private UnitOfMeasureService unitOfMeasureService;

    private WebDataBinder webDataBinder;


    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @InitBinder("ingredient")
    public void initBinder(WebDataBinder webDataBinder){
        this.webDataBinder = webDataBinder;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {

        model.addAttribute("recipe", recipeService.getRecipe(recipeId).block());

        return "recipe/ingredient/ingredients";
    }



    @GetMapping("recipe/{id}/ingredient/new")
    public String newIngredient(@PathVariable("id") String recipeId, Model model) {

        model.addAttribute("recipeId", recipeId);

        model.addAttribute("ingredient", new Ingredient());

        return "recipe/ingredient/ingredient-form";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id,
                                       Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id).block());
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable("recipeId") String recipeId,
                                         @PathVariable String id, Model model) {
        model.addAttribute("recipeId", recipeId);

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id).block());

        return "recipe/ingredient/ingredient-form";
    }

    @PostMapping("/ingredient/{recipeId}/save")
    public String saveOrUpdate(@ModelAttribute("ingredient") Ingredient ingredient,
                               @PathVariable String recipeId, Model model) {

        webDataBinder.validate();
        BindingResult result = webDataBinder.getBindingResult();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.error(objectError.getDefaultMessage());
            });


            return "recipe/ingredient/ingredient-form";
        }

        Ingredient savedIngredient = ingredientService.saveIngredient(ingredient, recipeId).block();

        return "redirect:/recipe/" + recipeId + "/ingredient/" + savedIngredient.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id) {

        ingredientService.deleteById(recipeId, id);

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @ModelAttribute("uomList")
    public List<UnitOfMeasure> populateUomList(){
        return unitOfMeasureService.listAllUoms().collectList().block();
    }
}
