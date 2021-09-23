package dev.patika.userservice.service;


import dev.patika.userservice.dto.requestDTO.CustomerRequestDTO;
import dev.patika.userservice.entity.Customer;
import dev.patika.userservice.exceptions.CustomerIsNotExist;
import dev.patika.userservice.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements BaseService<CustomerRequestDTO> {

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
        customer = customer.addAddress(customerRequestDTO.getAddress());

        customerRepository.save(customer);

        return Optional.of(customer);

    }

    @Override
    public Optional<Customer> update(CustomerRequestDTO customerRequestDTO) {

        Optional<Customer> customer = customerRepository.findById(customerRequestDTO.getId());

        if (customer.isPresent()) {

            Customer tempCustomer = customer.get();
            customer = Optional.of(modelMapper.map(customerRequestDTO, Customer.class));
            customer.get().setAddress(customer.get().getAddress());

            return Optional.of(customerRepository.save(customer.get()));
        }

        throw new CustomerIsNotExist("Customer not found");

    }

    @Override
    public void deleteById(long customerId) {
        customerRepository.deleteById(customerId);
    }

}
