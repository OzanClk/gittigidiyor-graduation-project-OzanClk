package dev.patika.userservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {


    @JoinColumn(name = "customer_id")
    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();

    public Customer addAddress(Address address) {
        this.address.add(address);
        return this;
    }

}
