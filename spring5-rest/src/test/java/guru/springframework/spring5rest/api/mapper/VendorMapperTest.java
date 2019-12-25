package guru.springframework.spring5rest.api.mapper;

import guru.springframework.spring5rest.api.model.VendorDTO;
import guru.springframework.spring5rest.domain.Vendor;
import org.junit.Assert;
import org.junit.Test;

public class VendorMapperTest {


    public static final String NAME = "someName";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() throws Exception {
        //given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        //when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        //then
        Assert.assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    public void vendorDTOtoVendor() throws Exception {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        //when
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);

        //then
        Assert.assertEquals(vendorDTO.getName(), vendor.getName());
    }

}