package guru.springframework.spring5rest.api.mapper;

import guru.springframework.spring5rest.api.model.CategoryDTO;
import guru.springframework.spring5rest.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO categoryToCategoryDTO(Category category) {
        if(category==null) {
            return null;
        }

        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }
}
