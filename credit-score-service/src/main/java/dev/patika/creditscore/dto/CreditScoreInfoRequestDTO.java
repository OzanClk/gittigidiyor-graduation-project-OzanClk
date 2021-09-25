package dev.patika.creditscore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/*import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;*/


@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CreditScoreInfoRequestDTO {


    //@NotEmpty
    //@Pattern(regexp = "[^0-9]")
    //@Valid
    private String firstName;


    //@NotEmpty
    //@Pattern(regexp = "[^0-9]")
    //@Valid
    private String lastName;


    //@NotEmpty
    //@Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$")
    //@Valid
    private String identificationNumber;


    //@NotEmpty
    //@Pattern(regexp = "^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$")
    //@Valid
    private String phoneNumber;


    //@NotNull
    //@Valid
    private double monthlyIncome;



}
