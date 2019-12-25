package guru.springframework.spring5webfluxrest.controller;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.exception.ResourceNotFoundException;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    Flux<Category> findAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    Mono<Category> findById(@PathVariable String id) {
        Category categoryFound = categoryRepository.findById(id).block();

        if (categoryFound == null) {
            throw new ResourceNotFoundException("Resource not found");
        }

        return categoryRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Mono<Category> saveCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    Mono<Category> updateCategory(@PathVariable String id, @RequestBody Category category) {

        Category categoryFound = categoryRepository.findById(id).block();

        if (categoryFound == null) {
            throw new ResourceNotFoundException("Resource not found");
        }

        category.setId(id);
        return categoryRepository.save(category);
    }

    @PatchMapping("/{id}")
    Mono<Category> patchCategory(@PathVariable String id, @RequestBody Category category) {

        Category foundCategory = categoryRepository.findById(id).block();

        if (foundCategory == null) {
            throw new ResourceNotFoundException("Resource not found");
        }

        if (!foundCategory.getDescription().equals(category.getDescription())) {
            foundCategory.setDescription(category.getDescription());
            return categoryRepository.save(foundCategory);
        }

        return Mono.just(foundCategory);
    }

    @DeleteMapping("/{id}")
    Mono<Void> deleteCategory(@PathVariable String id) {
        Category categoryFound = categoryRepository.findById(id).block();

        if (categoryFound == null) {
            throw new ResourceNotFoundException("Resource not found");
        }

        return categoryRepository.deleteById(id);
    }
}
