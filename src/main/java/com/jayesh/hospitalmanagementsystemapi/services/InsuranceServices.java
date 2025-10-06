package com.jayesh.hospitalmanagementsystemapi.services;

import com.jayesh.hospitalmanagementsystemapi.dto.InsuranceDTO;
import com.jayesh.hospitalmanagementsystemapi.dto.InsuranceResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.dto.PatientDTO;
import com.jayesh.hospitalmanagementsystemapi.dto.PatientResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.entity.Insurance;
import com.jayesh.hospitalmanagementsystemapi.entity.Patient;
import com.jayesh.hospitalmanagementsystemapi.repository.InsuranceRepo;
import com.jayesh.hospitalmanagementsystemapi.repository.PatientRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceServices {
    private final PatientRepo patientRepo;
    private final InsuranceRepo insuranceRepo;
    @Autowired
    public InsuranceServices(PatientRepo patientRepo, InsuranceRepo insuranceRepo) {
        this.patientRepo = patientRepo;
        this.insuranceRepo = insuranceRepo;
    }

    public InsuranceResponseDTO createInsurance(InsuranceDTO insuranceDTO) {
        Insurance insurance = new Insurance(insuranceDTO);
        Insurance temp = insuranceRepo.save(insurance);
        return new InsuranceResponseDTO(temp);
    }


    public PatientResponseDTO assignInsuranceToPatient(Long patientId, Long insuranceId){
        Patient patient = patientRepo.findById(patientId).orElseThrow(()->new EntityNotFoundException("Patient Not Found with Id : "+patientId));
        Insurance insurance = insuranceRepo.findById(insuranceId).orElseThrow(()->new EntityNotFoundException("Insurance Not Found with Id "+insuranceId));
        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        patientRepo.save(patient);
        return new PatientResponseDTO(patient);

    }

    public void removeInsuranceFromPatient(Long patientId){
        Patient patient = patientRepo.findById(patientId).orElseThrow(()->new EntityNotFoundException("Patient Not Found with Id : "+patientId));
        Insurance insurance = patient.getInsurance();
        if(insurance!=null) {
            patient.setInsurance(null);
            patientRepo.save(patient);
        }
    }

    public Boolean checkValidity(Long patientId){
        Patient patient = patientRepo.findById(patientId).orElseThrow(()->new EntityNotFoundException("Patient Not Found with Id : "+patientId));
        Insurance insurance = patient.getInsurance();
        if (insurance==null){
            return false;
        }

        LocalDate checkDate = insurance.getValidUntil();
        if(checkDate==null){
            return false;
        }

        return checkDate.isAfter(LocalDate.now());
    }


    public void deleteInsurance(Long id){
        insuranceRepo.deleteById(id);
    }

    public InsuranceResponseDTO updateInsurance(Long id,InsuranceDTO insuranceDTO){
        Insurance insurance = insuranceRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Insurance Not Found with Id "+id));
        if(insuranceDTO.getProviderName() != null){
            insurance.setProviderName(insuranceDTO.getProviderName());
        }
        if(insuranceDTO.getValidUntil() != null){
            insurance.setValidUntil(insuranceDTO.getValidUntil());
        }
        if(insuranceDTO.getPolicyNumber() != null){
            insurance.setPolicyNumber(insuranceDTO.getPolicyNumber());
        }
        Insurance temp =insuranceRepo.save(insurance);
        return new InsuranceResponseDTO(temp);
    }

    public InsuranceResponseDTO findInsuranceById(Long insuranceId){
        return new InsuranceResponseDTO(insuranceRepo
                .findById(insuranceId)
                .orElseThrow(()->new EntityNotFoundException("Insurance Not Found")));

    }

    public List<InsuranceResponseDTO> findInsurances(String providerName, String policyNumber, LocalDate validUntil) {
        List<Insurance> insurances;
        if(providerName!=null){
            insurances=insuranceRepo.findAllInsuranceByProviderName(providerName);
        }else if(policyNumber!=null){
            insurances=insuranceRepo.findAllInsuranceByPolicyNumber(policyNumber);
        }else if(validUntil!=null){
            insurances=insuranceRepo.findAllInsuranceByValidUntil(validUntil);
        }else{
            insurances=insuranceRepo.findAll();
        }
        return insurances.stream().map(InsuranceResponseDTO::new).collect(Collectors.toList());

    }

    public InsuranceResponseDTO updateInsuranceByPut(Long insuranceId, InsuranceDTO insuranceDTO){
        Insurance insurance = insuranceRepo.findById(insuranceId).orElseThrow(()->new EntityNotFoundException("Insurance Not Found with Id "+insuranceId));
        insurance.setProviderName(insuranceDTO.getProviderName());
        insurance.setValidUntil(insuranceDTO.getValidUntil());
        insurance.setPolicyNumber(insuranceDTO.getPolicyNumber());
        Insurance temp =insuranceRepo.save(insurance);
        return new InsuranceResponseDTO(temp);
    }

    public List<InsuranceResponseDTO> createMultipleInsurances(List<InsuranceDTO> insuranceDTOList) {
        // 1. DTOs ki list ko Entities ki list mein convert kar
        List<Insurance> insurances = insuranceDTOList.stream()
                .map(Insurance::new) // Har DTO se ek naya Insurance object banao
                .collect(Collectors.toList());

        // 2. Repository ko poori list ek saath save karne ko bolo
        List<Insurance> savedInsurances = insuranceRepo.saveAll(insurances);

        // 3. Saved entities ko Response DTOs mein convert karke return kar
        return savedInsurances.stream()
                .map(InsuranceResponseDTO::new)
                .collect(Collectors.toList());
    }




}
