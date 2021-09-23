package dev.patika.userservice.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
