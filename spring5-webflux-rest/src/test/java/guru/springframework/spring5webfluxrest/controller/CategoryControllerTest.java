package guru.springframework.spring5webfluxrest.controller;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CategoryControllerTest {

    WebTestClient webTestClient;
    CategoryRepository categoryRepository;
    CategoryController categoryController;

    @Before
    public void setUp() throws Exception {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryController = new CategoryController(categoryRepository);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    public void findAll() {
        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(Category.builder().description("Cat1").build(),
                        Category.builder().description("Cat2").build()));

        webTestClient.get()
                .uri("/api/categories")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    public void findById() {
        BDDMockito.given(categoryRepository.findById("someid"))
                .willReturn(Mono.just(Category.builder().description("Cat").build()));

        webTestClient.get()
                .uri("/api/categories/someid")
                .exchange()
                .expectBody(Category.class);
    }

    @Test
    public void testSaveCateogry() {
        BDDMockito.given(categoryRepository.save(ArgumentMatchers.any(Category.class)))
                .willReturn(Mono.just(Category.builder().build()));

        Mono<Category> catToSaveMono = Mono.just(Category.builder().description("Some Cat").build());

        webTestClient.post()
                .uri("/api/categories")
                .body(catToSaveMono, Category.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void TestUpdateCategory() {
        BDDMockito.given(categoryRepository.save(ArgumentMatchers.any(Category.class)))
                .willReturn(Mono.just(Category.builder().build()));

        Mono<Category> catToUpdateMono = Mono.just(Category.builder().description("Some Cat").build());

        webTestClient.put()
                .uri("/api/categories/asdfasdf")
                .body(catToUpdateMono, Category.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void testPatchWithChanges() {
        BDDMockito.given(categoryRepository.findById(ArgumentMatchers.anyString()))
                .willReturn(Mono.just(Category.builder().description("Description 1").build()));

        BDDMockito.given(categoryRepository.save(ArgumentMatchers.any(Category.class)))
                .willReturn(Mono.just(Category.builder().build()));

        Mono<Category> catToUpdateMono = Mono.just(Category.builder().description("New Description").build());

        webTestClient.patch()
                .uri("/api/categories/asdfasdf")
                .body(catToUpdateMono, Category.class)
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(categoryRepository).save(ArgumentMatchers.any());
    }

    @Test
    public void testPatchNoChanges() {
        BDDMockito.given(categoryRepository.findById(ArgumentMatchers.anyString()))
                .willReturn(Mono.just(Category.builder().description("abc").build()));

        BDDMockito.given(categoryRepository.save(ArgumentMatchers.any(Category.class)))
                .willReturn(Mono.just(Category.builder().build()));

        Mono<Category> catToUpdateMono = Mono.just(Category.builder().description("abc").build());

        webTestClient.patch()
                .uri("/api/categories/asdfasdf")
                .body(catToUpdateMono, Category.class)
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(categoryRepository, Mockito.never()).save(ArgumentMatchers.any());
    }

    @Test
    public void testDeleteCategory() {

        webTestClient.delete()
                .uri("/api/categories/someid")
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(categoryRepository).deleteById(ArgumentMatchers.anyString());
    }
}