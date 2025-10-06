package com.jayesh.hospitalmanagementsystemapi.dto;

import com.jayesh.hospitalmanagementsystemapi.entity.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceResponseDTO {
    private Long id;
    private String policyNumber;
    private String providerName;
    private LocalDate validUntil;

    public InsuranceResponseDTO(Insurance insurance) {
        this.id = insurance.getId();
        this.policyNumber = insurance.getPolicyNumber();
        this.providerName = insurance.getProviderName();
        this.validUntil = insurance.getValidUntil();
    }
}
