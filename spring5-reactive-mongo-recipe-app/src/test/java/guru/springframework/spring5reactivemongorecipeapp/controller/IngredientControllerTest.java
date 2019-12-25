package guru.springframework.spring5reactivemongorecipeapp.controller;

import guru.springframework.spring5reactivemongorecipeapp.domain.Ingredient;
import guru.springframework.spring5reactivemongorecipeapp.domain.Recipe;
import guru.springframework.spring5reactivemongorecipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5reactivemongorecipeapp.service.IngredientService;
import guru.springframework.spring5reactivemongorecipeapp.service.RecipeService;
import guru.springframework.spring5reactivemongorecipeapp.service.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Ignore;
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

public class IngredientControllerTest {

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    RecipeService recipeService;

    IngredientController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        //given

        when(recipeService.getRecipe(anyString())).thenReturn(Mono.just(new Recipe()));

        when(unitOfMeasureService.listAllUoms()).thenReturn(Flux.just(new UnitOfMeasure()));

        //when
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredients"))
                .andExpect(model().attributeExists("recipe"));

        //then
        verify(recipeService, times(1)).getRecipe(anyString());
    }

    @Test
    public void testShowIngredient() throws Exception {

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyString(), anyString())).
                thenReturn(Mono.just(new Ingredient()));

        when(unitOfMeasureService.listAllUoms()).thenReturn(Flux.just(new UnitOfMeasure()));

        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void testNewIngredientForm() throws Exception {
        //given

        when(unitOfMeasureService.listAllUoms()).thenReturn(Flux.just(new UnitOfMeasure()));

        //then
        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredient-form"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

        verify(unitOfMeasureService, times(1)).listAllUoms();

    }

    @Test
    public void testUpdateIngredientForm() throws Exception {

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyString(), anyString())).
                thenReturn(Mono.just( new Ingredient()));
        when(unitOfMeasureService.listAllUoms()).thenReturn(Flux.just(new UnitOfMeasure()));

        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredient-form"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

        verify(ingredientService, times(1)).findByRecipeIdAndIngredientId(anyString(), anyString());
        verify(unitOfMeasureService, times(1)).listAllUoms();
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId("3");

        //when
        when(ingredientService.saveIngredient(any(), anyString())).thenReturn(Mono.just(ingredient));

        when(unitOfMeasureService.listAllUoms()).thenReturn(Flux.just(new UnitOfMeasure()));

        //then
        mockMvc.perform(post("/ingredient/2/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "some description")
                .param("amount", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));

    }

    @Test
    public void testSaveOrUpdateValidationFormFail() throws Exception {

        //when
        when(ingredientService.saveIngredient(any(), anyString())).thenReturn(Mono.just( new Ingredient()));

        when(unitOfMeasureService.listAllUoms()).thenReturn(Flux.just(new UnitOfMeasure()));

        //then
        mockMvc.perform(post("/ingredient/2/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "some description")
                .param("amount", "0.5"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredient-form"));

    }

    @Test
    public void testDeleteIngredient() throws Exception {

        when(ingredientService.deleteById(anyString(), anyString())).thenReturn(Mono.empty());

        when(unitOfMeasureService.listAllUoms()).thenReturn(Flux.just(new UnitOfMeasure()));

        //then
        mockMvc.perform(get("/recipe/2/ingredient/3/delete")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredients"));

        verify(ingredientService, times(1)).deleteById(anyString(), anyString());

    }
}