package dev.patika.creditscore.service;

import dev.patika.creditscore.entity.CreditScoreInformation;
import dev.patika.creditscore.repository.CreditApplicationApprovalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreditScoreServiceImplTest {

    @InjectMocks
    CreditScoreServiceImpl creditScoreService;

    @Mock
    CreditApplicationApprovalRepository creditApplicationApprovalRepository;


    @Test
    void getCustomerByIdNumber() {

        List<CreditScoreInformation> creditInfo = new ArrayList<>();

        Optional<List<CreditScoreInformation>> expected = Optional.of(creditInfo);

        Mockito.when(creditApplicationApprovalRepository.findByIdentificationNumber(Mockito.any())).thenReturn(expected);

        Optional<List<CreditScoreInformation>> actual = creditScoreService.getCustomerByIdNumber(Mockito.any());

        assertEquals(expected, actual);

    }

    @Test
    void creditApplicationDateTime() {



    }
}