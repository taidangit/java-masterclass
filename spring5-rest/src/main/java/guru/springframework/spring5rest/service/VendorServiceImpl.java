package guru.springframework.spring5rest.service;

import guru.springframework.spring5rest.api.mapper.VendorMapper;
import guru.springframework.spring5rest.api.model.VendorDTO;
import guru.springframework.spring5rest.api.model.VendorListDTO;
import guru.springframework.spring5rest.controller.VendorController;
import guru.springframework.spring5rest.domain.Vendor;
import guru.springframework.spring5rest.exception.ResourceNotFoundException;
import guru.springframework.spring5rest.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private VendorMapper vendorMapper;
    private VendorRepository vendorRepository;

    @Autowired
    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorListDTO getAllVendors() {
        List<VendorDTO> vendorDTOS = vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());

        return new VendorListDTO(vendorDTOS);
    }

    @Override
    public VendorDTO saveVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDTOtoVendor(vendorDTO));
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendorToSave = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendorToSave.setId(id);

        return saveAndReturnDTO(vendorToSave);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(savedVendor);

        returnDTO.setUrl(getVendorUrl(savedVendor.getId()));

        return returnDTO;
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }


    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {

                    if (vendorDTO.getName() != null) {
                        vendor.setName(vendorDTO.getName());
                    }

                    return saveAndReturnDTO(vendor);
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }


}
