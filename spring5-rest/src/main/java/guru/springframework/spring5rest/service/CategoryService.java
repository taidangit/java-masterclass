package guru.springframework.spring5rest.service;

import guru.springframework.spring5rest.api.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
