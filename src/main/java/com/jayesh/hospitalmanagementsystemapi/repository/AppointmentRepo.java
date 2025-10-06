package com.jayesh.hospitalmanagementsystemapi.repository;

import com.jayesh.hospitalmanagementsystemapi.dto.AppointmentDTO;
import com.jayesh.hospitalmanagementsystemapi.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {

    List<Appointment> findAllAppointmentsByDoctor_IdAndAppointmentDate(Long doctorId, LocalDate appointmentDate);
    List<Appointment> findAllAppointmentsByPatient_Id(Long patientId);
    List<Appointment> findAllByPatientIdAndAppointmentDateAfter(Long patientId, LocalDate date);

}
