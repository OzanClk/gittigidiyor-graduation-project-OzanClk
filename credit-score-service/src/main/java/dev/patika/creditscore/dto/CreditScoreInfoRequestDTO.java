package dev.patika.creditscore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CreditScoreInfoRequestDTO {


    @ApiModelProperty(
            dataType = "String",
            example = "Koray",
            position = 1)
    private String firstname;


    @ApiModelProperty(
            dataType = "String",
            example = "Güney",
            position = 2)
    private String lastname;


    @ApiModelProperty(
            dataType = "String",
            example = "5554443322",
            position = 3)
    private String identificationNumber;


    @ApiModelProperty(
            dataType = "String",
            example = "05324423413",
            position = 4)
    private String phoneNumber;


    @ApiModelProperty(
            dataType = "Double",
            example = "5600",
            position = 5)
    private double monthlyIncome;



}
