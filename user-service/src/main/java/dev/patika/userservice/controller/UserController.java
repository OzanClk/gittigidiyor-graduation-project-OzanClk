package dev.patika.userservice.controller;


import dev.patika.userservice.dto.requestDTO.CustomerRequestDTO;
import dev.patika.userservice.entity.Customer;
import dev.patika.userservice.exceptions.CustomerIsNotExist;
import dev.patika.userservice.repository.CustomerRepository;
import dev.patika.userservice.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    CustomerRepository customerRepository;


    @PostMapping("/save-customers")
    public ResponseEntity<Customer> customerSave(@RequestBody CustomerRequestDTO customerRequestDTO) {

        return new ResponseEntity<>(customerService.save(customerRequestDTO).get(), HttpStatus.OK);

    }

    @PutMapping("/update-customers/{customerId}")
    public ResponseEntity<Customer> customerUpdate(@Valid @RequestBody CustomerRequestDTO customerRequestDTO,
                                                   @PathVariable Long customerId) {

        return new ResponseEntity<>(customerService.update(customerRequestDTO, customerId).get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-customers/{customerId}")
    public ResponseEntity<Map<String, Boolean>> customerDelete(@PathVariable Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerIsNotExist("Customer not found id: " + customerId));

        Map<String, Boolean> response = customerService.deleteById(customerId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-customers")
    public ResponseEntity<Iterable<Customer>> customersFindAll() {
        return new ResponseEntity<>(customerService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/get-customers/{customerId}")
    public ResponseEntity<Customer> customersFindById(@PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.findById(customerId).get(), HttpStatus.OK);
    }

}
