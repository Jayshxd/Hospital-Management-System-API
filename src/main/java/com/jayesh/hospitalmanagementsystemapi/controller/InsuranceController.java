package com.jayesh.hospitalmanagementsystemapi.controller;

import com.jayesh.hospitalmanagementsystemapi.dto.InsuranceDTO;
import com.jayesh.hospitalmanagementsystemapi.dto.InsuranceResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.services.InsuranceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/insurances")
public class InsuranceController {

    private final InsuranceServices  insuranceServices;
    @Autowired
    public InsuranceController(InsuranceServices insuranceServices) {
        this.insuranceServices = insuranceServices;
    }


    @GetMapping
    public ResponseEntity<List<InsuranceResponseDTO>> findInsurances(
            @RequestParam(required = false) String providerName,
            @RequestParam(required = false) String policyNumber,
            @RequestParam(required = false) LocalDate validUntil
    ){
        List<InsuranceResponseDTO> insuranceList =
              insuranceServices.findInsurances(providerName, policyNumber, validUntil);
        if(insuranceList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(insuranceList);
    }

    @PostMapping
    public ResponseEntity<InsuranceResponseDTO> createInsuranceForPatient(
            @RequestBody InsuranceDTO insuranceDTO
    ){
      InsuranceResponseDTO insurance = insuranceServices.createInsurance(insuranceDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(insurance);
    }


    @GetMapping("/{insuranceId}")
    public ResponseEntity<InsuranceResponseDTO> findInsuranceById(@PathVariable Long insuranceId){
        InsuranceResponseDTO insurance = insuranceServices.findInsuranceById(insuranceId);
        return ResponseEntity.ok(insurance);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<InsuranceResponseDTO> updateInsuranceForPatient(
            @PathVariable Long id,
            @RequestBody InsuranceDTO insuranceDTO
    ){
        InsuranceResponseDTO insurance =  insuranceServices.updateInsurance(id, insuranceDTO);
        return ResponseEntity.ok(insurance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsuranceResponseDTO> updateInsuranceForPatientByPut(
            @PathVariable Long id,
            @RequestBody InsuranceDTO insuranceDTO
    ){
        InsuranceResponseDTO insurance =  insuranceServices.updateInsuranceByPut(id, insuranceDTO);
        return ResponseEntity.ok(insurance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsuranceForPatient(
            @PathVariable Long id
    ){
        insuranceServices.deleteInsurance(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<InsuranceResponseDTO>> createMultipleInsurances(
            @RequestBody List<InsuranceDTO> insuranceDTOList) {

        List<InsuranceResponseDTO> savedInsurances = insuranceServices.createMultipleInsurances(insuranceDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInsurances);
    }








}
