package dev.patika.userservice.service;

import dev.patika.userservice.dto.requestDTO.AddressDTO;
import dev.patika.userservice.dto.requestDTO.CustomerRequestDTO;
import dev.patika.userservice.entity.Customer;
import dev.patika.userservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerServiceImpl;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    ModelMapper modelMapper;

    @Test
    void findByAll() {

        Iterable<Customer> expected = new Iterable<Customer>() {
            @Override
            public Iterator<Customer> iterator() {
                return null;
            }
        };

        Mockito.when(customerServiceImpl.findByAll()).thenReturn(expected);

        Iterable<Customer> actual = customerRepository.findAll();

        assertEquals(expected, actual);


    }

    @Test
    void save() {

        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO(1, "ali", "veli", "aliveli@hotmail.com", "aliveli", "053425623"
                , new AddressDTO(1,"TR","IST","BAHCELIEVLER"));
        Customer customer = new Customer();
        Optional<Customer> expected = Optional.of(customer);

        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(expected);


        Optional<Customer> actual = customerServiceImpl.save(customerRequestDTO);

        assertEquals(expected, actual);


    }

    @Test
    void findById() {


        Customer customer = new Customer();

        Optional<Customer> expected = Optional.of(customer);

        Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(expected);

        Customer actual = customerServiceImpl.findById(Mockito.any()).get();

        assertEquals(expected.get(), actual);

    }
}