package com.jayesh.hospitalmanagementsystemapi.controller;

import com.jayesh.hospitalmanagementsystemapi.dto.AppointmentResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.dto.DoctorDTO;
import com.jayesh.hospitalmanagementsystemapi.dto.DoctorResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("public/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialty
    ) {
        List<DoctorResponseDTO> temp = doctorService.findDoctors(name, specialty);
        return ResponseEntity.ok().body(temp);
    }

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> createDoctor(@RequestBody DoctorDTO doctorDTO){
        DoctorResponseDTO temp = doctorService.addDoctor(doctorDTO);
        return ResponseEntity.ok().body(temp);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@PathVariable Long id,@RequestBody DoctorDTO doctorDTO){
        DoctorResponseDTO temp = doctorService.updateDoctorDetails(id, doctorDTO);
        return ResponseEntity.ok().body(temp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctorPut(@PathVariable Long id,@RequestBody DoctorDTO doctorDTO){
        DoctorResponseDTO temp = doctorService.updateDoctorDetailsByPut(id,doctorDTO);
        return ResponseEntity.ok().body(temp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        doctorService.removeDoctorById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id){
        DoctorResponseDTO temp = doctorService.getDoctorById(id);
        return ResponseEntity.ok().body(temp);
    }

    @GetMapping("/{id}/appointment")
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointmentsForDoctor(
            @PathVariable Long id,
            @RequestParam LocalDate localDate){
        List<AppointmentResponseDTO> temp = doctorService.getAllAppointmentsForDoctorOnADate(id, localDate);
        return ResponseEntity.ok().body(temp);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<DoctorResponseDTO>> createMultipleDoctors(
            @RequestBody List<DoctorDTO> doctorDTOList) {

        List<DoctorResponseDTO> savedDoctors = doctorService.addMultipleDoctors(doctorDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctors);
    }

}
