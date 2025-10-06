package com.jayesh.hospitalmanagementsystemapi.services;

import com.jayesh.hospitalmanagementsystemapi.dto.AppointmentResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.dto.DoctorDTO;
import com.jayesh.hospitalmanagementsystemapi.dto.DoctorResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.entity.Appointment;
import com.jayesh.hospitalmanagementsystemapi.entity.Doctor;
import com.jayesh.hospitalmanagementsystemapi.repository.AppointmentRepo;
import com.jayesh.hospitalmanagementsystemapi.repository.DoctorRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepo doctorRepo;
    private final AppointmentRepo appointmentRepo;
    private final AppointmentServices appointmentServices;

    @Autowired
    public DoctorService(DoctorRepo doctorRepo, AppointmentRepo appointmentRepo, AppointmentServices appointmentServices) {
        this.doctorRepo = doctorRepo;
        this.appointmentRepo = appointmentRepo;
        this.appointmentServices = appointmentServices;
    }

    public DoctorResponseDTO addDoctor(DoctorDTO  doctorDTO) {
        Doctor doctor = new Doctor(doctorDTO);
        Doctor temp = doctorRepo.save(doctor);
        return new DoctorResponseDTO(temp);
    }


    public DoctorResponseDTO getDoctorById(Long id){
        Doctor doctor = doctorRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Doctor not found"));
        return new DoctorResponseDTO(doctor);
    }


    public List<DoctorResponseDTO> findDoctors(String name,String specialty){
        List<Doctor> doctors;
        if(name!=null){
            doctors=doctorRepo.findAllDoctorByDoctorName(name);
        }else if(specialty!=null){
            doctors=doctorRepo.findAllDoctorByDoctorSpecialty(specialty);
        }else{
            doctors=doctorRepo.findAll();
        }
        return doctors.stream().map(DoctorResponseDTO::new).collect(Collectors.toList());
    }

    public void removeDoctorById(Long id){
        doctorRepo.deleteById(id);
    }

    public DoctorResponseDTO updateDoctorDetails(Long id,DoctorDTO doctorDTO){

        Doctor doctor =doctorRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Doctor not found"));
        if(doctorDTO.getDoctorName() != null){
            doctor.setDoctorName(doctorDTO.getDoctorName());
        }
        if(doctorDTO.getDoctorSpecialty() != null){
            doctor.setDoctorSpecialty(doctorDTO.getDoctorSpecialty());
        }
        Doctor temp = doctorRepo.save(doctor);
        return new DoctorResponseDTO(temp);
    }

    public DoctorResponseDTO updateDoctorDetailsByPut(Long id,DoctorDTO doctorDTO){

        Doctor doctor =doctorRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Doctor not found"));
        doctor.setDoctorSpecialty(doctorDTO.getDoctorSpecialty());
        doctor.setDoctorName(doctorDTO.getDoctorName());
        Doctor temp = doctorRepo.save(doctor);
        return new DoctorResponseDTO(temp);
    }

    public List<AppointmentResponseDTO> getAllAppointmentsForDoctorOnADate(Long doctorId, LocalDate localDate){
        return appointmentServices.getAppointmentsForDoctorOnDate(doctorId, localDate);
    }


    public List<DoctorResponseDTO> addMultipleDoctors(List<DoctorDTO> doctorDTOList) {
        // 1. DTOs ki list ko Entities mein convert kar
        List<Doctor> doctors = doctorDTOList.stream()
                .map(Doctor::new)
                .collect(Collectors.toList());

        // 2. Repository se poori list ek saath save karwa
        List<Doctor> savedDoctors = doctorRepo.saveAll(doctors);

        // 3. Saved entities ko Response DTOs mein convert karke return kar
        return savedDoctors.stream()
                .map(DoctorResponseDTO::new)
                .collect(Collectors.toList());
    }


}
