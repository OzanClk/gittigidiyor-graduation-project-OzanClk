package dev.patika.creditscore.controller;

import dev.patika.creditscore.dto.CreditScoreInfoRequestDTO;
import dev.patika.creditscore.entity.CreditScoreInformation;
import dev.patika.creditscore.service.CreditScoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CreditScoreController {

    @Autowired
    CreditScoreServiceImpl creditScoreService;


    @PostMapping("/creditApplication")
    public ResponseEntity<String> creditApplication(@RequestBody CreditScoreInfoRequestDTO creditScoreInfoRequestDTO) {

        return new ResponseEntity<>(creditScoreService.creditScore(creditScoreInfoRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/get-customer-by-identification-number/{identificationNumber}")
    public ResponseEntity<List<CreditScoreInformation>> getCustomerByIdentificationNumber(@PathVariable String identificationNumber) {
        return new ResponseEntity<>(creditScoreService.getCustomerByIdNumber(identificationNumber).get(), HttpStatus.OK);
    }


}
