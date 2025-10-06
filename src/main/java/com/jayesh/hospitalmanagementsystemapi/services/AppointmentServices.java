package com.jayesh.hospitalmanagementsystemapi.services;

import com.jayesh.hospitalmanagementsystemapi.dto.AppointmentDTO;
import com.jayesh.hospitalmanagementsystemapi.dto.AppointmentResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.dto.DoctorDTO;
import com.jayesh.hospitalmanagementsystemapi.dto.DoctorResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.entity.Appointment;
import com.jayesh.hospitalmanagementsystemapi.entity.Doctor;
import com.jayesh.hospitalmanagementsystemapi.entity.Patient;
import com.jayesh.hospitalmanagementsystemapi.repository.AppointmentRepo;
import com.jayesh.hospitalmanagementsystemapi.repository.DoctorRepo;
import com.jayesh.hospitalmanagementsystemapi.repository.PatientRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServices {


    private final AppointmentRepo appointmentRepo;
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    //private final DoctorService doctorService;
    @Autowired
    public AppointmentServices(AppointmentRepo appointmentRepo, PatientRepo patientRepo, DoctorRepo doctorRepo) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo=patientRepo;
        this.doctorRepo=doctorRepo;
        //this.doctorService =doctorService;
    }

    public AppointmentResponseDTO bookAppointment(AppointmentDTO appointmentDTO) {
        Long patientId = appointmentDTO.getPatientId();
        Long doctorId = appointmentDTO.getDoctorId();
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient not found of id : "+patientId));
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(()->new EntityNotFoundException("Doctor not found of id : "+doctorId));
        Appointment appointment = new Appointment(appointmentDTO);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        Appointment temp = appointmentRepo.save(appointment);
        return new AppointmentResponseDTO(temp);
    }


    public void cancelAppointment(Long id){
        appointmentRepo.deleteById(id);
    }

    public AppointmentResponseDTO rescheduleAppointment(Long id, AppointmentDTO appointmentDTO){
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Appointment not found"));
        if(appointmentDTO.getAppointmentTime()!=null){
            appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        }
        if(appointmentDTO.getAppointmentDate()!=null){
            appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        }
        Appointment p =appointmentRepo.save(appointment);
        return new AppointmentResponseDTO(p);
    }

    public List<AppointmentResponseDTO> getAppointmentsForDoctorOnDate(Long doctorId, LocalDate localDate){
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found of id: " + doctorId));

        if(localDate==null){
            throw new EntityNotFoundException("Local date not found");
        }
        List<Appointment> appointments =
                appointmentRepo.findAllAppointmentsByDoctor_IdAndAppointmentDate(doctorId, localDate);
        return appointments.stream().map(AppointmentResponseDTO::new).collect(Collectors.toList());
    }



    public List<AppointmentResponseDTO> getAllUpcomingAppointmentsForPatient(Long patientId){
        List<Appointment> appointments = appointmentRepo.findAllByPatientIdAndAppointmentDateAfter(patientId, LocalDate.now());
        return appointments.stream().map(AppointmentResponseDTO::new).collect(Collectors.toList());
    }


    public List<AppointmentResponseDTO> getListOfAppointments(){
        return appointmentRepo.findAll().stream().map(AppointmentResponseDTO::new).collect(Collectors.toList());
    }

}
