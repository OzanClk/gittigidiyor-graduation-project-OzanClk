package dev.patika.creditscore.controller;

import dev.patika.creditscore.dto.CreditScoreInfoRequestDTO;
import dev.patika.creditscore.entity.CreditScoreInformation;
import dev.patika.creditscore.service.CreditScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CreditScoreController {

    @Autowired
    CreditScoreService creditScoreService;


    @PostMapping("/creditApp")
    public ResponseEntity<String> creditApp(@RequestBody CreditScoreInfoRequestDTO creditScoreInfoRequestDTO) {

        return new ResponseEntity<>(creditScoreService.creditScore(creditScoreInfoRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/get-customer-by-identification-number/{identificationNumber}")
    public ResponseEntity<CreditScoreInformation> getCustomerByIdentificationNumber(@PathVariable String identificationNumber) {
        return new ResponseEntity<>(creditScoreService.getCustomerByIdNumber(identificationNumber).get(), HttpStatus.OK);
    }


}
