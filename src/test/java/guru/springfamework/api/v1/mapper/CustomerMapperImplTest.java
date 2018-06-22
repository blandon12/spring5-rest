package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerMapperImplTest {

    @Test
    public void customerMapperReturnDTO() {
        // given
        Customer customer = new Customer();
        customer.setId(Long.valueOf(1L));
        customer.setFirstName("Tom");
        customer.setLastName("Dean");
        // when
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
        // then
        assertEquals("Tom", customerDTO.getFirstName());
    }
}