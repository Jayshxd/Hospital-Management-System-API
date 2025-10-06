package com.jayesh.hospitalmanagementsystemapi.repository;

import com.jayesh.hospitalmanagementsystemapi.dto.InsuranceDTO;
import com.jayesh.hospitalmanagementsystemapi.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InsuranceRepo extends JpaRepository<Insurance, Long> {

    List<Insurance> findAllInsuranceByProviderName(String providerName);

    List<Insurance> findAllInsuranceByPolicyNumber(String policyNumber);

    List<Insurance> findAllInsuranceByValidUntil(LocalDate validUntil);
}
