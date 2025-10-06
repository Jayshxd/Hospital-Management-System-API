package com.jayesh.hospitalmanagementsystemapi.services;

import com.jayesh.hospitalmanagementsystemapi.dto.*;
import com.jayesh.hospitalmanagementsystemapi.entity.Insurance;
import com.jayesh.hospitalmanagementsystemapi.entity.Patient;
import com.jayesh.hospitalmanagementsystemapi.enums.BloodGroups;
import com.jayesh.hospitalmanagementsystemapi.enums.Gender;
import com.jayesh.hospitalmanagementsystemapi.enums.Status;
import com.jayesh.hospitalmanagementsystemapi.repository.AppointmentRepo;
import com.jayesh.hospitalmanagementsystemapi.repository.DoctorRepo;
import com.jayesh.hospitalmanagementsystemapi.repository.InsuranceRepo;
import com.jayesh.hospitalmanagementsystemapi.repository.PatientRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {


    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final InsuranceRepo  insuranceRepo;
    private final AppointmentRepo appointmentRepo;
    private final InsuranceServices insuranceServices;
    private final AppointmentServices appointmentServices;

    @Autowired
    public PatientService(PatientRepo patientRepo, DoctorRepo doctorRepo, InsuranceRepo  insuranceRepo, AppointmentRepo appointmentRepo, InsuranceServices insuranceServices, AppointmentServices appointmentServices) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
        this.insuranceRepo = insuranceRepo;
        this.appointmentRepo = appointmentRepo;
        this.insuranceServices = insuranceServices;
        this.appointmentServices = appointmentServices;
    }


    public PatientResponseDTO getPatientById(Long id){
        Patient patient = patientRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Patient not found"));
        return new PatientResponseDTO(patient);
    }


    public PatientResponseDTO updatePatientDetails(Long id, PatientDTO  patientDTO){
        Patient patient = patientRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Patient not found"));
        if(patientDTO.getName() != null){
            patient.setName(patientDTO.getName());
        }
        if(patientDTO.getBloodGroup() != null){
            patient.setBloodGroup(patientDTO.getBloodGroup());
        }
        if(patientDTO.getGender() != null){
            patient.setGender(patientDTO.getGender());
        }
        patientRepo.save(patient);
        return new PatientResponseDTO(patient);
    }



    public PatientResponseDTO addPatient(PatientDTO patientDTO){
        Patient patient = new Patient(patientDTO);
        Patient savedPatient =patientRepo.save(patient);
        System.out.println("Patient added successfully");
        return new PatientResponseDTO(savedPatient);
    }


    public List<AppointmentDTO> getAllAppointmentsOfPatient(Long id){
        return appointmentRepo.findAllAppointmentsByPatient_Id(id).stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }


    public InsuranceResponseDTO getInsuranceDetails(Long id){
        Patient patient = patientRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Patient not found"));

        Insurance insurance = patient.getInsurance();
        if (insurance==null){
            throw new EntityNotFoundException("Insurance not found");
        }
        return new InsuranceResponseDTO(insurance);
    }

    public void dischargePatient(Long id){
        Patient patient = patientRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Patient not found"));
        patient.setStatus(Status.DISCHARGED);
        patientRepo.save(patient);
    }

    public PatientResponseDTO updatePatientDetailsByPut(Long id ,PatientDTO patientDTO){
        Patient patient = patientRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Patient not found"));
        patient.setName(patientDTO.getName());
        patient.setBloodGroup(patientDTO.getBloodGroup());
        patient.setGender(patientDTO.getGender());
        patient.setStatus(patientDTO.getStatus());
        patientRepo.save(patient);
        return new PatientResponseDTO(patient);
    }


    public void deletePatient(Long id){
        patientRepo.deleteById(id);
    }

    // PatientService.java

    public List<PatientResponseDTO> findPatients(String name, BloodGroups bloodGroup, Gender gender) {
        List<Patient> patients;

        // Logic: Check karo ki konsa filter aaya hai
        if (name != null) {
            patients = patientRepo.findAllPatientByName(name);
        } else if (bloodGroup != null) {
            patients = patientRepo.findAllPatientByBloodGroup(bloodGroup);
        } else if (gender != null) {
            patients = patientRepo.findAllPatientByGender(gender);
        } else {
            // Agar koi filter nahi hai, toh saare patients le aao
            patients = patientRepo.findAll();
        }

        // Result ko DTO mein convert karke return karo
        return patients.stream()
                .map(PatientResponseDTO::new)
                .collect(Collectors.toList());
    }


    public Boolean checkValidity(Long patientId){
        if(patientRepo.existsById(patientId)){
            return insuranceServices.checkValidity(patientId);
        }
        throw new EntityNotFoundException("Patient Not Found with Id : "+patientId);
    }

    public PatientResponseDTO assignInsuranceToThePatient(Long patientId, Long insuranceId){
        return insuranceServices.assignInsuranceToPatient(patientId, insuranceId);
    }
    public void removeInsuranceFromPatient(Long id){
        insuranceServices.removeInsuranceFromPatient(id);
    }


    public List<AppointmentResponseDTO> getAllUpcomingAppointmentsForPatient(Long patientId){
        return appointmentServices.getAllUpcomingAppointmentsForPatient(patientId);
    }


    public List<PatientResponseDTO> addMultiplePatients(List<PatientDTO> patientDTOList) {
        // 1. DTO ki list ko Entity ki list mein convert karo
        List<Patient> patients = patientDTOList.stream()
                .map(Patient::new) // Har DTO se ek naya Patient object banao
                .collect(Collectors.toList());

        // 2. Repository ko poori list ek saath save karne ko bolo. Yeh zyada fast hai.
        List<Patient> savedPatients = patientRepo.saveAll(patients);

        // 3. Save hui entities ki list ko Response DTO ki list mein convert karke return karo
        return savedPatients.stream()
                .map(PatientResponseDTO::new)
                .collect(Collectors.toList());
    }


}
