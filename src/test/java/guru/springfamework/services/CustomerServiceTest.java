package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapper customerMapper;

    private CustomerService customerService;

    private Customer customer1;

    private CustomerDTO customerDTO1;

    private List<Customer> customerList;

    @Before
    public void setUp() {
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);

        customer1 = new Customer();
        customer1.setId(Long.valueOf(1L));
        customer1.setFirstName("Tom");
        customer1.setLastName("Lee");

        customerDTO1 = new CustomerDTO();
        customerDTO1.setId(Long.valueOf(1L));
        customerDTO1.setFirstName("Tom");
        customerDTO1.setLastName("Lee");

        customerList = new ArrayList<>();
    }

    @Test
    public void getAllCustomersReturnCustomerList() {
        // given
        customerList.add(customer1);
        customerList.add(new Customer());
        when(customerRepository.findAll()).thenReturn(customerList);
        // when
        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
        // then
        assertEquals(2, customerDTOList.size());
    }

    @Test
    public void getCustomerByFirstNameReturnCustomerDTO() {
        // given
        when(customerRepository.findByFirstName("Tom")).thenReturn(customer1);
        when(customerMapper.customerToCustomerDTO(customer1)).thenReturn(customerDTO1);
        // when
        CustomerDTO customerDTO = customerService.getCustomerByFirstName("Tom");
        // then
        assertEquals(Long.valueOf(1L), customerDTO.getId());
        assertEquals("Tom", customerDTO.getFirstName());
        assertEquals("Lee", customerDTO.getLastName());
    }

    @Test
    public void getCustomerByLastNameReturnCustomerDTO() {
        // given
        when(customerRepository.findByLastName("Tom")).thenReturn(customer1);
        when(customerMapper.customerToCustomerDTO(customer1)).thenReturn(customerDTO1);
        // when
        CustomerDTO customerDTO = customerService.getCustomerByLastName("Tom");
        // then
        assertEquals(Long.valueOf(1L), customerDTO.getId());
        assertEquals("Tom", customerDTO.getFirstName());
        assertEquals("Lee", customerDTO.getLastName());
    }
}