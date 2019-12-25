package guru.springframework.spring5rest.controller;

import guru.springframework.spring5rest.api.model.VendorDTO;
import guru.springframework.spring5rest.api.model.VendorListDTO;
import guru.springframework.spring5rest.service.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest extends AbstractRestControllerTest {

    @MockBean //provided by Spring Context
    VendorService vendorService;

    @Autowired
    MockMvc mockMvc; //provided by Spring Context

    VendorDTO vendorDTO1;
    VendorDTO vendorDTO2;

    @Before
    public void setUp() throws Exception {

        vendorDTO1=new VendorDTO();
        vendorDTO1.setName("Vendor 1");
        vendorDTO1.setUrl(VendorController.BASE_URL+"/1");

        vendorDTO2=new VendorDTO();
        vendorDTO2.setName("Vendor 2");
        vendorDTO2.setUrl(VendorController.BASE_URL+"/2");
    }

    @Test
    public void getVendorList() throws Exception {

        VendorListDTO vendorListDTO=new VendorListDTO(Arrays.asList(vendorDTO1,vendorDTO2));

        given(vendorService.getAllVendors()).willReturn(vendorListDTO);

        mockMvc.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void getVendorById() throws Exception {

        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO1);

        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())));
    }

    @Test
    public void saveVendor() throws Exception {

        given(vendorService.saveVendor(any())).willReturn(vendorDTO1);

        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())));
    }

    @Test
    public void updateVendor() throws Exception {

        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO1);
        given(vendorService.updateVendor(anyLong(), any())).willReturn(vendorDTO1);

        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())));
    }

    @Test
    public void patchVendor() throws Exception {
        given(vendorService.patchVendor(anyLong(), any())).willReturn(vendorDTO1);

        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())));
    }

    @Test
    public void deleteVendor() throws Exception {
        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO1);
        mockMvc.perform(delete(VendorController.BASE_URL + "/1"))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyLong());

    }
}