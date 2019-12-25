package guru.springframework.controller;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exception.NotFoundException;
import guru.springframework.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    private RecipeController controller;

    private MockMvc mockMvc;

    @Mock
    private Model model;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        controller = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
    }

    @Test
    public void testGetRecipeNotFound() throws Exception {

        when(recipeService.findById(anyString())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isNotFound())
        .andExpect(view().name("recipe/404error"));
    }


    @Test
    public void findAll() throws Exception {

        Set<Recipe> recipes = new HashSet<>();

        Recipe recipe1=new Recipe();
        recipe1.setId("1");
        recipes.add(recipe1);

        Recipe recipe2 = new Recipe();
        recipe1.setId("1");
        recipes.add(recipe2);

        when(recipeService.findAll()).thenReturn(recipes);


        mockMvc.perform(get("/recipe/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipes"))
                .andExpect(model().attributeExists("recipes"));;
    }

    @Test
    public void showById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeService.findById(anyString())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testGetNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();

        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipe-form"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testPostNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId("2");

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "some description")
                .param("prepTime", "100")
                .param("cookTime", "100")
                .param("servings", "100")
                .param("source", "some source")
                .param("url", "http://www.thymeleaf.org"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("redirect:/recipe/show/2"));
        verify(recipeService, times(1)).saveRecipeCommand(any());
    }


    @Test
    public void testPostNewRecipeFormValidationFail() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId("2");

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "some description")
                .param("prepTime", "100")
                .param("cookTime", "100")
                .param("servings", "101")
                .param("source", "some source")
                .param("url", "http://www.thymeleaf.org"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/recipe-form"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId("2");

        when(recipeService.findCommandById(anyString())).thenReturn(command);

        mockMvc.perform(get("/recipe/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipe-form"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/recipe/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/list"));

        verify(recipeService, times(1)).deleteById(anyString());
    }
}