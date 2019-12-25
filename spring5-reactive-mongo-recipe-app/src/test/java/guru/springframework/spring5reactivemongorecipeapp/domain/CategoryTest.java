package guru.springframework.spring5reactivemongorecipeapp.domain;

import guru.springframework.spring5reactivemongorecipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    private Category category;

    @Before
    public void setup() {
        category=new Category();
    }

    @Test
    public void getId() {
        String idValue="4";

        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}