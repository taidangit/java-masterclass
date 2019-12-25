package guru.springframework.spring5rest.controller;

import guru.springframework.spring5rest.api.model.VendorDTO;
import guru.springframework.spring5rest.api.model.VendorListDTO;
import guru.springframework.spring5rest.exception.ResourceNotFoundException;
import guru.springframework.spring5rest.service.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is my Vendor API")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/vendors";

    @Autowired
    private VendorService vendorService;

    @ApiOperation(value = "View List of Vendors", notes = "These are some API Notes")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getVendorList() {
        return vendorService.getAllVendors();
    }

    @ApiOperation(value = "Get Vendor By Id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @ApiOperation(value = "Save a Vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO saveVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendor(vendorDTO);
    }

    @ApiOperation(value = "Update an existing Vendor")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {

        VendorDTO vendor = vendorService.getVendorById(id);
        if (vendor == null) {
            throw new ResourceNotFoundException();
        }

        return vendorService.updateVendor(id, vendorDTO);
    }
    @ApiOperation(value = "Update a Vendor Property")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }

    @ApiOperation(value = "Delete a Vendor")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {

        VendorDTO vendor = vendorService.getVendorById(id);
        if (vendor == null) {
            throw new ResourceNotFoundException();
        }

        vendorService.deleteVendorById(id);
    }
}
