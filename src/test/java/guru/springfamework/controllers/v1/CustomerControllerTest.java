package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    public static final String FIRSTNAME = "Jim";
    public static final String LASTNAME = "Jones";

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void listAllCustomers() throws Exception {
        // given
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setId(Long.valueOf(1L));
        customerDTO1.setFirstName(FIRSTNAME);
        customerDTO1.setLastName(LASTNAME);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(Long.valueOf(2L));
        customerDTO2.setFirstName("Jack");
        customerDTO2.setLastName("Last");

        List<CustomerDTO> customerDTOList = Arrays.asList(customerDTO1, customerDTO2);
        when(customerService.getAllCustomers()).thenReturn(customerDTOList);
        // then
        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    public void listCustomerByFirstName() throws Exception {
        // given
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setId(Long.valueOf(1L));
        customerDTO1.setFirstName(FIRSTNAME);
        customerDTO1.setLastName(LASTNAME);
        when(customerService.getCustomerByFirstName(FIRSTNAME)).thenReturn(customerDTO1);
        // then
        mockMvc.perform(get("/api/v1/customers/firstname/Jim").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRSTNAME)));
    }

    @Test
    public void listCustomerByLastName() throws Exception {
        // given
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setId(Long.valueOf(1L));
        customerDTO1.setFirstName(FIRSTNAME);
        customerDTO1.setLastName(LASTNAME);
        when(customerService.getCustomerByLastName(LASTNAME)).thenReturn(customerDTO1);
        // then
        mockMvc.perform(get("/api/v1/customers/lastname/Jones").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", equalTo(LASTNAME)));
    }
}