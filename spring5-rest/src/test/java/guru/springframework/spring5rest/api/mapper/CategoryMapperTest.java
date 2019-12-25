package guru.springframework.spring5rest.api.mapper;

import guru.springframework.spring5rest.api.model.CategoryDTO;
import guru.springframework.spring5rest.domain.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CategoryMapperTest {

    public static final String NAME = "Joe";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void categoryToCategoryDTO() throws Exception {

        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        Assert.assertEquals(Long.valueOf(ID), categoryDTO.getId());
        Assert.assertEquals(NAME, categoryDTO.getName());
    }

}