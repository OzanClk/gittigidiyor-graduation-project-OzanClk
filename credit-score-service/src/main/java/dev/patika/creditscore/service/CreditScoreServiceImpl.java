package dev.patika.creditscore.service;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class CreditScoreServiceImpl {

    @Autowired
    ModelMapper modelMapper;

    CreditApplicationApprovalRepository creditApplicationApprovalRepository;

    @Autowired
    public CreditScoreServiceImpl(CreditApplicationApprovalRepository creditApplicationApprovalRepository) {
        this.creditApplicationApprovalRepository = creditApplicationApprovalRepository;
    }


    public String creditScore(CreditScoreInfoRequestDTO creditScoreInfoRequestDTO) {

        CreditScoreInformation creditScoreInformation = new CreditScoreInformation();

        creditScoreInformation = creditLimitCalculate(creditScoreInfoRequestDTO, creditScoreInformation);


        creditScoreInformation.setCreditApplicationDate(creditApplicationDateTime());

        creditApplicationApprovalRepository.save(creditScoreInformation);

        sendSMS(creditScoreInformation);

        if (creditScoreInformation.getCreditResult() == CreditResult.CONFIRM)
            return "Your loan application has been " + CreditResult.CONFIRM + " Your loan limit is " + creditScoreInformation.getCreditLimit() + " $";
        else
            return "Your loan application has been" + CreditResult.REFUSE;


    }

    public CreditScoreInformation creditLimitCalculate(CreditScoreInfoRequestDTO creditScoreInfoRequestDTO, CreditScoreInformation creditScoreInformation) {

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


    public Optional<List<CreditScoreInformation>> getCustomerByIdNumber(String identificationNumber) {

        if (creditApplicationApprovalRepository.findByIdentificationNumber(identificationNumber).isPresent()) {
            return creditApplicationApprovalRepository.findByIdentificationNumber(identificationNumber);
        }

        throw new NoUserExistWithThisID("No user exist with this Identification Number");

    }

    public String creditApplicationDateTime() {

        LocalDateTime CreditApplicationTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formatDateTime = CreditApplicationTime.format(format);

        return formatDateTime;
    }

    public void sendSMS(CreditScoreInformation creditScoreInformation) {

        String smsMessage = "";
        String phoneNumber = "+90"+creditScoreInformation.getPhoneNumber();

        if (creditScoreInformation.getCreditResult() == CreditResult.CONFIRM) {
            smsMessage = "Dear " + creditScoreInformation.getFirstName() + " " + creditScoreInformation.getLastName() + " Credit Application has been CONFIRM. Your limit " + creditScoreInformation.getCreditLimit() + " $";
        } else {
            smsMessage = "Dear " + creditScoreInformation.getFirstName() + " " + creditScoreInformation.getLastName() + "Credit Application has been REFUSE.";
        }

        Message message = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber("+15153033555"), smsMessage).create();

        if ("sent".equals(message.getStatus()) || "queued".equals(message.getStatus())) {
            System.out.println("SMS Sended");
        }


    }


}
