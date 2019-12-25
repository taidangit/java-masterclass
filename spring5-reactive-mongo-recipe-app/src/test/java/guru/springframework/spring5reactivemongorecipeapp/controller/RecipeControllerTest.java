package guru.springframework.spring5reactivemongorecipeapp.controller;

import guru.springframework.spring5reactivemongorecipeapp.command.RecipeCommand;
import guru.springframework.spring5reactivemongorecipeapp.domain.Recipe;
import guru.springframework.spring5reactivemongorecipeapp.exception.NotFoundException;
import guru.springframework.spring5reactivemongorecipeapp.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testREcipes() throws Exception {
        //given
        Recipe recipe = new Recipe();
        when(recipeService.getRecipes()).thenReturn(Flux.just(recipe));

        //when
        mockMvc.perform(get("/recipe/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipes"))
                .andExpect(model().attributeExists("recipes"));

        //then
        verify(recipeService, times(1)).getRecipes();
    }

    @Test
    public void testGetRecipe() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeService.getRecipe(anyString())).thenReturn(Mono.just(recipe));

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testGetRecipeNotFound() throws Exception {

        when(recipeService.getRecipe(anyString())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("recipe/404error"));
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
        RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setId("2");

        when(recipeService.saveRecipeCommand(any())).thenReturn(Mono.just(recipeCommand));

        mockMvc.perform(post("/recipe/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "some description")
                .param("prepTime", "100")
                .param("cookTime", "100")
                .param("servings", "100")
                .param("source", "some source")
                .param("url", "http://www.thymeleaf.org"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/show/2"));

        verify(recipeService, times(1)).saveRecipeCommand(any());

    }

    @Test
    public void testPostNewRecipeFormValidationFail() throws Exception {

        when(recipeService.saveRecipeCommand(any())).thenReturn(Mono.just(new RecipeCommand()));

        mockMvc.perform(post("/recipe/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "some description")
                .param("prepTime", "100")
                .param("cookTime", "100")
                .param("servings", "101")
                .param("source", "some source")
                .param("url", "http://www.thymeleaf.org")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/recipe-form"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("2");

        when(recipeService.getRecipe(anyString())).thenReturn(Mono.just(recipe));

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