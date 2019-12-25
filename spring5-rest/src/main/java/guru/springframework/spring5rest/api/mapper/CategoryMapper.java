package guru.springframework.spring5rest.api.mapper;

import guru.springframework.spring5rest.api.model.CategoryDTO;
import guru.springframework.spring5rest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
