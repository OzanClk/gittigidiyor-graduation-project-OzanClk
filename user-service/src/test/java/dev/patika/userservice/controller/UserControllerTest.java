package dev.patika.userservice.controller;

import dev.patika.userservice.dto.requestDTO.CustomerRequestDTO;
import dev.patika.userservice.entity.Customer;
import dev.patika.userservice.service.CustomerServiceImpl;
import org.checkerframework.checker.nullness.Opt;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    @InjectMocks
    UserController userController;

    @Mock
    CustomerServiceImpl customerServiceImpl;

    @Test
    void customerSave() {

        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        Customer customer = new Customer();
        customer.setFirstname("Ali");
        Optional<Customer> expected = Optional.of(customer);

        Mockito.when(customerServiceImpl.save(Mockito.any(CustomerRequestDTO.class))).thenReturn(expected);


        Customer actual = userController.customerSave(customerRequestDTO).getBody();

        customerServiceImpl.save(Mockito.any());


        assertAll(
                () -> assertEquals(expected.get(), actual),
                () -> assertEquals(expected.get().getFirstname(), actual.getFirstname()),
                () -> Mockito.verify(customerServiceImpl, times(2)).save(Mockito.any())
        );


    }

    @Test
    void customersFindAll() {

        Iterable<Customer> expected = new Iterable<Customer>() {
            @Override
            public Iterator<Customer> iterator() {
                return null;
            }
        };

        Mockito.when(customerServiceImpl.findByAll()).thenReturn(expected);

        Iterable<Customer> actual = userController.customersFindAll().getBody();

        assertEquals(expected, actual);

    }


    @Test
    void customersFindById() {

        Customer customer = new Customer();

        Optional<Customer> expected = Optional.of(customer);

        Mockito.when(customerServiceImpl.findById(Mockito.any())).thenReturn(expected);

        Customer actual = userController.customersFindById(Mockito.any()).getBody();

        assertEquals(expected.get(), actual);


    }
}