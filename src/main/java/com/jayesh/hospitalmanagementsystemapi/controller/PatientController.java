package com.jayesh.hospitalmanagementsystemapi.controller;

import com.jayesh.hospitalmanagementsystemapi.dto.*;
import com.jayesh.hospitalmanagementsystemapi.enums.BloodGroups;
import com.jayesh.hospitalmanagementsystemapi.enums.Gender;
import com.jayesh.hospitalmanagementsystemapi.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/patients")
public class PatientController {

    private final PatientService patientService;
    @Autowired
    PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping // Yeh base /patients par lagega
    public ResponseEntity<List<PatientResponseDTO>> findPatients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BloodGroups bloodGroup,
            @RequestParam(required = false) Gender gender
    ) {
        // Controller ka kaam sirf request lena aur service ko bhejna hai
        List<PatientResponseDTO> patientDTOList = patientService.findPatients(name, bloodGroup, gender);

        if (patientDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patientDTOList);
    }
    @PostMapping
    public ResponseEntity<PatientResponseDTO> savePatient(@RequestBody PatientDTO patientDTO) {
        PatientResponseDTO p = patientService.addPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id,@RequestBody PatientDTO patientDTO) {
        PatientResponseDTO p =patientService.updatePatientDetails(id,patientDTO);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatientByPut(@PathVariable Long id,@RequestBody PatientDTO patientDTO) {
        PatientResponseDTO p =patientService.updatePatientDetailsByPut(id,patientDTO);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }



    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id){
        PatientResponseDTO p = patientService.getPatientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }




    @PostMapping("/{id}/discharge")
    public ResponseEntity<Void> dischargePatient(@PathVariable Long id){
        patientService.dischargePatient(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    //insurance methods


    @GetMapping("/{id}/insurance")
    public ResponseEntity<InsuranceResponseDTO> getAllTheInsurancesByPatientId(@PathVariable Long id){
        InsuranceResponseDTO temp = patientService.getInsuranceDetails(id);
        if(temp == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(temp);
    }

    @GetMapping("/{patientId}/insurance/validity")
    public ResponseEntity<Boolean> checkValidity(
            @PathVariable Long patientId
    ){
        Boolean check = patientService.checkValidity(patientId);
        return ResponseEntity.ok(check);
    }

    @PutMapping("/{patientId}/insurance/{insuranceId}")
    public ResponseEntity<PatientResponseDTO> assignInsuranceToPatient(
            @PathVariable Long patientId,
            @PathVariable Long insuranceId
    ){
        PatientResponseDTO p = patientService.assignInsuranceToThePatient(patientId, insuranceId);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{patientId}/insurance")
    public ResponseEntity<Void> removeInsuranceFromPatient(
            @PathVariable Long patientId
    ){
        patientService.removeInsuranceFromPatient(patientId);
        return ResponseEntity.noContent().build();
    }


    //appointment

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> getAllTheAppointmentsOfPatientByPatientId(@PathVariable Long id){
        List<AppointmentResponseDTO> temp = patientService.getAllUpcomingAppointmentsForPatient(id);
        if(temp.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(temp);
    }


    @PostMapping("/bulk") // Naya endpoint -> POST /patients/bulk
    public ResponseEntity<List<PatientResponseDTO>> saveMultiplePatients(
            @RequestBody List<PatientDTO> patientDTOList) {

        List<PatientResponseDTO> savedPatients = patientService.addMultiplePatients(patientDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatients);
    }



}
