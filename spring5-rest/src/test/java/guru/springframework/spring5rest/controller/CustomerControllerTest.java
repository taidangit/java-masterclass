package guru.springframework.spring5rest.controller;

import guru.springframework.spring5rest.api.model.CustomerDTO;
import guru.springframework.spring5rest.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    CustomerDTO customerDTO1;
    CustomerDTO customerDTO2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        customerDTO1 = new CustomerDTO();
        customerDTO1.setId(1L);
        customerDTO1.setFirstName("Michale");
        customerDTO1.setLastName("Weston");
        customerDTO1.setUrl(CustomerController.BASE_URL+"/1");

        customerDTO2 = new CustomerDTO();
        customerDTO2.setId(2L);
        customerDTO2.setFirstName("Sam");
        customerDTO2.setLastName("Axe");
        customerDTO2.setUrl(CustomerController.BASE_URL+"/2");
    }

    @Test
    public void testListCustomers() throws Exception {

        //given
        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customerDTO1, customerDTO2));

        mockMvc.perform(get(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception {

        //given
        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO1);

        //when
        mockMvc.perform(get(CustomerController.BASE_URL+"/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michale")));
    }

    @Test
    public void saveCustomer() throws Exception {
        //given
        when(customerService.saveCustomer(ArgumentMatchers.any())).thenReturn(customerDTO1);

        //when/then
        mockMvc.perform(post(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Michale")))
                .andExpect(jsonPath("$.lastName", equalTo("Weston")))
                .andExpect(jsonPath("$.url", equalTo(CustomerController.BASE_URL+"/1")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        //given
        when(customerService.updateCustomer(anyLong(), ArgumentMatchers.any())).thenReturn(customerDTO1);

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO1);

        //when/then
        mockMvc.perform(put(CustomerController.BASE_URL+"/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michale")))
                .andExpect(jsonPath("$.lastName", equalTo("Weston")))
                .andExpect(jsonPath("$.url", equalTo(CustomerController.BASE_URL+"/1")));
    }

    @Test
    public void testPatchCustomer() throws Exception {

        //given
        when(customerService.patchCustomer(anyLong(), ArgumentMatchers.any())).thenReturn(customerDTO1);

        mockMvc.perform(patch(CustomerController.BASE_URL+"/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michale")))
                .andExpect(jsonPath("$.lastName", equalTo("Weston")))
                .andExpect(jsonPath("$.url", equalTo(CustomerController.BASE_URL+"/1")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO1);

        mockMvc.perform(delete(CustomerController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }
}