package guru.springframework.spring5webfluxrest.controller;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class VendorControlerTest {

    WebTestClient webTestClient;
    VendorRepository vendorRepository;
    VendorControler vendorControler;


    @Before
    public void setUp() throws Exception {
        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorControler = new VendorControler(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorControler).build();
    }

    @Test
    public void findAll() {
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(Vendor.builder().firstName("Fred").lastName("Flintstone").build(),
                        Vendor.builder().firstName("Barney").lastName("Rubble").build()));

        webTestClient.get()
                .uri("/api/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    public void findById() {
        BDDMockito.given(vendorRepository.findById("someid"))
                .willReturn(Mono.just(Vendor.builder().firstName("Jimmy").lastName("Johns").build()));

        webTestClient.get()
                .uri("/api/vendors/someid")
                .exchange()
                .expectBody(Vendor.class);
    }

    @Test
    public void testSaveVendor() {
        BDDMockito.given(vendorRepository.save(ArgumentMatchers.any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> vendorToSaveMono = Mono.just(Vendor.builder().firstName("First Name")
                .lastName("Last Name").build());

        webTestClient.post()
                .uri("/api/vendors")
                .body(vendorToSaveMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void TestUpdateVendor() {
        BDDMockito.given(vendorRepository.save(ArgumentMatchers.any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> vendorToUpdateMono = Mono.just(Vendor.builder().firstName("First Name")
                .lastName("Last Name").build());

        webTestClient.put()
                .uri("/api/vendors/asdfasdf")
                .body(vendorToUpdateMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void testPatchVendorWithChanges() {

        BDDMockito.given(vendorRepository.findById(ArgumentMatchers.anyString()))
                .willReturn(Mono.just(Vendor.builder().firstName("Jimmy").build()));

        BDDMockito.given(vendorRepository.save(ArgumentMatchers.any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> vendorMonoToUpdate = Mono.just(Vendor.builder().firstName("Jim").build());

        webTestClient.patch()
                .uri("/api/vendors/someid")
                .body(vendorMonoToUpdate, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(vendorRepository).save(ArgumentMatchers.any());
    }

    @Test
    public void testPatchVendorWithoutChanges() {

        BDDMockito.given(vendorRepository.findById(ArgumentMatchers.anyString()))
                .willReturn(Mono.just(Vendor.builder().firstName("Jimmy").build()));

        BDDMockito.given(vendorRepository.save(ArgumentMatchers.any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> vendorMonoToUpdate = Mono.just(Vendor.builder().firstName("Jimmy").build());

        webTestClient.patch()
                .uri("/api/vendors/someid")
                .body(vendorMonoToUpdate, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(vendorRepository, Mockito.never()).save(ArgumentMatchers.any());
    }

    @Test
    public void testDeleteVendor() {

        webTestClient.delete()
                .uri("/api/vendors/someid")
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(vendorRepository).deleteById(ArgumentMatchers.anyString());
    }
}