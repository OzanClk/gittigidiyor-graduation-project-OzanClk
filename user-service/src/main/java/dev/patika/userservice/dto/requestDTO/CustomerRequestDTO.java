package dev.patika.userservice.dto.requestDTO;


import dev.patika.userservice.entity.Address;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {

    @ApiModelProperty(hidden = true,example = "Enter the customer_id for the update method")
    private long id;

    @ApiModelProperty(
            dataType = "String",
            example = "Koray",
            position = 1)
    @NotEmpty
    @Pattern(regexp = "[^0-9]")
    private String firstname;

    @ApiModelProperty(
            dataType = "String",
            example = "Güney",
            position = 2)
    @NotEmpty
    private String lastname;

    @ApiModelProperty(
            dataType = "String",
            example = "koray@gmail.com",
            position = 3)
    @Email
    private String email;

    @ApiModelProperty(
            dataType = "String",
            example = "KorayGuney",
            position = 4)
    @NotEmpty
    private String userName;


    @ApiModelProperty(
            dataType = "String",
            example = "*********",
            position = 5)
    @NotEmpty
    private String password;

    @ApiModelProperty(
            dataType = "String",
            example = "554325234",
            position = 6)
    @NotEmpty
    private String phoneNumber;

    @ApiModelProperty(
            dataType = "String",
            example = "admin",
            position = 7)
    @NotEmpty
    private String userType;

    @ApiModelProperty(position = 8)
    @NotNull
    private Address address;


}
