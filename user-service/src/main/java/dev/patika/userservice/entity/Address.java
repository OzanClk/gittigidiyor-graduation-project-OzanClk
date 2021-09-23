package dev.patika.userservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String country;
    @NotEmpty
    private String city;
    @NotEmpty
    private String district;
    @NotEmpty
    private String streetAddress;

    @NotNull
    private int apartmentNumber;
    @NotNull
    private int houseNumber;

    @NotNull
    private int zipCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Customer customer;


}
