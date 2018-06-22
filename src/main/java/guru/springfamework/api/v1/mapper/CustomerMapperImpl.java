package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {

        if (customer == null) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());

        return customerDTO;
    }
}
