package dev.patika.userservice.mapper;


import dev.patika.userservice.dto.requestDTO.CustomerRequestDTO;
import dev.patika.userservice.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public abstract class CustomerMapper {

    public abstract Customer mappingFromCustomerRequestDTOtoCustomer(CustomerRequestDTO customerRequestDTO);

}
