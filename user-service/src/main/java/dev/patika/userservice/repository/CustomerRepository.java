package dev.patika.userservice.repository;

import dev.patika.userservice.entity.Customer;
import dev.patika.userservice.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
