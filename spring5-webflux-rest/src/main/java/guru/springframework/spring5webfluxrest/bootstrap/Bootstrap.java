package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count().block() == 0) {
            loadCategories();
        }
        if (vendorRepository.count().block() == 0) {
            loadVendors();
        }
    }

    private void loadVendors() {
        vendorRepository.save(Vendor.builder().firstName("Joe").lastName("Buck").build()).block();

        vendorRepository.save(Vendor.builder().firstName("Micheal").lastName("Weston").build()).block();

        vendorRepository.save(Vendor.builder().firstName("Jessie").lastName("Waters").build()).block();

        vendorRepository.save(Vendor.builder().firstName("Bill").lastName("Nershi").build()).block();

        vendorRepository.save(Vendor.builder().firstName("Jimmy").lastName("Buffett").build()).block();

        log.info("Loaded Vendors: " + vendorRepository.count().block());
    }

    private void loadCategories() {
        categoryRepository.save(Category.builder().description("Fruits").build()).block();

        categoryRepository.save(Category.builder().description("Nuts").build()).block();

        categoryRepository.save(Category.builder().description("Breads").build()).block();

        categoryRepository.save(Category.builder().description("Meats").build()).block();

        categoryRepository.save(Category.builder().description("Eggs").build()).block();

        log.info("Loaded Categories: " + categoryRepository.count().block());
    }
}
