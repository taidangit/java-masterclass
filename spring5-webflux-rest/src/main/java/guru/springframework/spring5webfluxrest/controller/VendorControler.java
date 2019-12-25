package guru.springframework.spring5webfluxrest.controller;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.exception.ResourceNotFoundException;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/vendors")
public class VendorControler {

    private VendorRepository vendorRepository;

    @Autowired
    public VendorControler(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping
    Flux<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    @GetMapping("/{id}")
    Mono<Vendor> findById(@PathVariable String id) {
        Vendor vendorFound=vendorRepository.findById(id).block();
        if (vendorFound == null) {
            throw new ResourceNotFoundException("Resource not found");
        }
        return vendorRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Mono<Vendor> saveVendor(@RequestBody Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @PutMapping("/{id}")
    Mono<Vendor> updateVendor(@PathVariable String id, @RequestBody Vendor vendor) {
        Vendor vendorFound=vendorRepository.findById(id).block();
        if (vendorFound == null) {
            throw new ResourceNotFoundException("Resource not found");
        }

        vendor.setId(id);
        return vendorRepository.save(vendor);
    }

    @PatchMapping("/{id}")
    Mono<Vendor> patch(@PathVariable String id, @RequestBody Vendor vendor){

        Vendor foundVendor = vendorRepository.findById(id).block();

        if (foundVendor == null) {
            throw new ResourceNotFoundException("Resource not found");
        }

        if(!foundVendor.getFirstName().equals(vendor.getFirstName())){
            foundVendor.setFirstName(vendor.getFirstName());

            return vendorRepository.save(foundVendor);
        }
        return Mono.just(foundVendor);
    }

    @DeleteMapping("/{id}")
    Mono<Void> deleteVendor(@PathVariable String id) {
        Vendor vendorFound=vendorRepository.findById(id).block();
        if (vendorFound == null) {
            throw new ResourceNotFoundException("Resource not found");
        }

        return vendorRepository.deleteById(id);
    }
}
