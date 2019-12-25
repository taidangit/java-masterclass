package guru.springframework.spring5rest.controller;

import guru.springframework.spring5rest.api.model.CategoryDTO;
import guru.springframework.spring5rest.api.model.CatorgoryListDTO;
import guru.springframework.spring5rest.exception.ResourceNotFoundException;
import guru.springframework.spring5rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/categories";

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CatorgoryListDTO> getAllCatetories() {

        return new ResponseEntity<CatorgoryListDTO>(
                new CatorgoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {

        CategoryDTO category = categoryService.getCategoryByName(name);
        if (category == null) {
            throw new ResourceNotFoundException();
        }

        return new ResponseEntity<CategoryDTO>(
                category, HttpStatus.OK
        );
    }
}
