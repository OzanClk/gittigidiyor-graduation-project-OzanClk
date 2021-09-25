package dev.patika.creditscore.repository;

import dev.patika.creditscore.entity.CreditScoreInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditApplicationApprovalRepository extends MongoRepository<CreditScoreInformation, String> {

    Optional<CreditScoreInformation> findByIdentificationNumber(String identificationNumber);

}
