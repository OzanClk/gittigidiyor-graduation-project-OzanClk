package dev.patika.creditscore.service;

import dev.patika.creditscore.dto.CreditScoreInfoRequestDTO;
import dev.patika.creditscore.entity.CreditScoreInformation;

import java.util.List;
import java.util.Optional;

public interface CreditScoreService {

    public CreditScoreInformation creditLimitCalculate(CreditScoreInfoRequestDTO creditScoreInfoRequestDTO, CreditScoreInformation creditScoreInformation);

    public String creditScore(CreditScoreInfoRequestDTO creditScoreInfoRequestDTO);

    public Optional<List<CreditScoreInformation>> getCustomerByIdNumber(String identificationNumber);

    public String creditApplicationDateTime();


}
