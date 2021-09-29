package dev.patika.creditscore.entity;


import dev.patika.creditscore.entity.enumeration.CreditResult;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;


@Document
public class CreditScoreInformation {

    @Id
    private String Id;

    @Field
    private String firstname;

    @Field
    private String lastname;

    @Field
    private String identificationNumber;

    @Field
    private String phoneNumber;

    @Field
    private double monthlyIncome;

    @Field
    private CreditResult creditResult;

    @Field
    private double creditLimit;

    @Field
    private String creditApplicationDate;

    public String getCreditApplicationDate() {
        return creditApplicationDate;
    }

    public void setCreditApplicationDate(String creditApplicationDate) {
        this.creditApplicationDate = creditApplicationDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public CreditResult getCreditResult() {
        return creditResult;
    }

    public void setCreditResult(CreditResult creditResult) {
        this.creditResult = creditResult;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }
}
