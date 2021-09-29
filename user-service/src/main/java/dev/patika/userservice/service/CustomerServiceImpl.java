package dev.patika.userservice.service;


import dev.patika.userservice.dto.requestDTO.CustomerRequestDTO;
import dev.patika.userservice.entity.Address;
import dev.patika.userservice.entity.Customer;
import dev.patika.userservice.exceptions.CustomerIsNotExist;
import dev.patika.userservice.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements BaseService<CustomerRequestDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Iterable<Customer> findByAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> save(CustomerRequestDTO customerRequestDTO) {

        Customer customer = modelMapper.map(customerRequestDTO, Customer.class);
        Address address = modelMapper.map(customerRequestDTO.getAddress(),Address.class);
        customer = customer.addAddress(address);

        customerRepository.save(customer);

        return Optional.of(customer);

    }

    @Override
    public Optional<Customer> update(CustomerRequestDTO customerRequestDTO, Long customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {

            Customer tempCustomer = customer.get();
            customer = Optional.of(modelMapper.map(customerRequestDTO, Customer.class));
            customer.get().setAddress(customer.get().getAddress());
            customer.get().setId(customerId);

            return Optional.of(customerRepository.save(customer.get()));
        }

        throw new CustomerIsNotExist("Customer not found");

    }

    @Override
    public Map<String, Boolean> deleteById(long customerId) {

        customerRepository.deleteById(customerId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;


    }

    @Override
    public Optional<Customer> findById(Long customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            return customer;
        }

        throw new CustomerIsNotExist("Customer not found");

    }

}
