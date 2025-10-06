package com.jayesh.hospitalmanagementsystemapi.entity;

import com.jayesh.hospitalmanagementsystemapi.dto.InsuranceDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "patient_policy_number", unique = true, nullable = false, length = 10)
    private String policyNumber;
    @Column(name = "insurance_provider_name", nullable = false)
    private String providerName;
    @Column(nullable = false)
    private LocalDate validUntil;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "insurance")
    private Patient patient;


    public Insurance(String policyNumber, String providerName, LocalDate validUntil) {
        this.policyNumber = policyNumber;
        this.providerName = providerName;
        this.validUntil = validUntil;
    }
    public Insurance(InsuranceDTO insuranceDTO) {
        this.policyNumber = insuranceDTO.getPolicyNumber();
        this.providerName = insuranceDTO.getProviderName();
        this.validUntil = insuranceDTO.getValidUntil();
    }
}
