package guru.springframework.springmvcrest.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRestControllerTest {

    @Autowired
    CustomerRestController customerRestController;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllCustomers() {
    }

    @Test
    public void getCustomerById() {

    }

    @Test
    public void saveCustomer() {
    }

    @Test
    public void updateCustomer() {
    }

    @Test
    public void deleteCustomer() {
    }
}