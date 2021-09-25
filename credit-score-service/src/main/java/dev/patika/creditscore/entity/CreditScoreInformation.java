package dev.patika.creditscore.entity;


import dev.patika.creditscore.entity.enumeration.CreditResult;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document
public class CreditScoreInformation {

    @Id
    private String Id;

    @Field
    private String firstName;

    @Field
    private String lastName;

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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
