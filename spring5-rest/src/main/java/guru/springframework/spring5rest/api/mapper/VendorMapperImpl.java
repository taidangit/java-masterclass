package guru.springframework.spring5rest.api.mapper;

import guru.springframework.spring5rest.api.model.VendorDTO;
import guru.springframework.spring5rest.domain.Vendor;
import org.springframework.stereotype.Component;

@Component
public class VendorMapperImpl implements VendorMapper {
    @Override
    public VendorDTO vendorToVendorDTO(Vendor vendor) {
        if(vendor==null) {
            return null;
        }

        VendorDTO vendorDTO=new VendorDTO();
        vendorDTO.setId(vendor.getId());
        vendorDTO.setName(vendor.getName());


        return vendorDTO;
    }

    @Override
    public Vendor vendorDTOtoVendor(VendorDTO vendorDTO) {
        if(vendorDTO==null) {
            return null;
        }

        Vendor vendor=new Vendor();
        vendor.setId(vendorDTO.getId());
        vendor.setName(vendorDTO.getName());

        return vendor;
    }
}
