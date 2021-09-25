package dev.patika.creditscore.service;


import dev.patika.creditscore.dto.CreditScoreInfoRequestDTO;
import dev.patika.creditscore.entity.CreditScoreInformation;
import dev.patika.creditscore.entity.enumeration.CreditResult;
import dev.patika.creditscore.exceptions.NoUserExistWithThisID;
import dev.patika.creditscore.exceptions.WrongIdentificationNumber;
import dev.patika.creditscore.repository.CreditApplicationApprovalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditScoreService {

    @Autowired
    ModelMapper modelMapper;

    CreditApplicationApprovalRepository creditApplicationApprovalRepository;

    @Autowired
    public CreditScoreService(CreditApplicationApprovalRepository creditApplicationApprovalRepository) {
        this.creditApplicationApprovalRepository = creditApplicationApprovalRepository;
    }


    public String creditScore(CreditScoreInfoRequestDTO creditScoreInfoRequestDTO) {

        CreditScoreInformation creditScoreInformation = new CreditScoreInformation();

        creditScoreInformation = creditLimitCalculate(creditScoreInfoRequestDTO, creditScoreInformation);

        creditApplicationApprovalRepository.save(creditScoreInformation);

        if (creditScoreInformation.getCreditResult() == CreditResult.CONFIRM)
            return "Your loan application has been confirmed. Your loan limit is " + creditScoreInformation.getCreditLimit() + "$";
        else
            return "Your loan application has been refused";


    }

    private CreditScoreInformation creditLimitCalculate(CreditScoreInfoRequestDTO creditScoreInfoRequestDTO, CreditScoreInformation creditScoreInformation) {

        long lastDigitOfIdentificationNumber = Long.valueOf(creditScoreInfoRequestDTO.getIdentificationNumber()) % 10;

        if (lastDigitOfIdentificationNumber % 2 == 1)
            throw new WrongIdentificationNumber("Wrong Identification Number");

        creditScoreInformation = modelMapper.map(creditScoreInfoRequestDTO, CreditScoreInformation.class);

        switch ((int) lastDigitOfIdentificationNumber) {
            case 0:
            case 4:
                creditScoreInformation.setCreditLimit(creditScoreInfoRequestDTO.getMonthlyIncome() * 4);
                creditScoreInformation.setCreditResult(CreditResult.CONFIRM);
                break;
            case 2:
            case 8:
                if (creditScoreInfoRequestDTO.getMonthlyIncome() < 5000) {
                    creditScoreInformation.setCreditLimit(10_000);
                    creditScoreInformation.setCreditResult(CreditResult.CONFIRM);
                } else if (creditScoreInfoRequestDTO.getMonthlyIncome() >= 5000) {
                    creditScoreInformation.setCreditLimit(20_000);
                    creditScoreInformation.setCreditResult(CreditResult.CONFIRM);
                }
                break;
            case 6:
                creditScoreInformation.setCreditLimit(0);
                creditScoreInformation.setCreditResult(CreditResult.REFUSE);
        }

        return creditScoreInformation;

    }


    public Optional<CreditScoreInformation> getCustomerByIdNumber(String identificationNumber) {

        if (creditApplicationApprovalRepository.findByIdentificationNumber(identificationNumber).isPresent()) {
            return creditApplicationApprovalRepository.findByIdentificationNumber(identificationNumber);
        }

        throw new NoUserExistWithThisID("No user exist with this Identification Number");

    }
}
