package dev.patika.userservice.controller;


import dev.patika.userservice.dto.requestDTO.CustomerRequestDTO;
import dev.patika.userservice.entity.Customer;
import dev.patika.userservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    CustomerService customerService;


    @PostMapping("/save-customers")
    public ResponseEntity<Customer> customerSave(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {

        return new ResponseEntity<>(customerService.save(customerRequestDTO).get(), HttpStatus.OK);

    }

    @PutMapping("/update-customers")
    public ResponseEntity<Customer> customerUpdate(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        return new ResponseEntity<>(customerService.update(customerRequestDTO).get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-customers/{customerId}")
    public ResponseEntity<String> customerDelete(@PathVariable Long customerId) {
        customerService.deleteById(customerId);
        return new ResponseEntity<>("Customer with id number  " + customerId + " has been deleted", HttpStatus.OK);
    }

    @GetMapping("/get-customers")
    public ResponseEntity<Iterable<Customer>> instructorsFindAll() {
        return new ResponseEntity<>(customerService.findByAll(), HttpStatus.OK);
    }

}
