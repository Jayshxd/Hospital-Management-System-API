package com.jayesh.hospitalmanagementsystemapi.dto;

import com.jayesh.hospitalmanagementsystemapi.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

    private Long patientId;
    private Long doctorId;
    private String reason;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;


    public AppointmentDTO(Appointment appointment) {
        this.reason = appointment.getReason();
        this.appointmentDate = appointment.getAppointmentDate();
        this.appointmentTime = appointment.getAppointmentTime();
        this.patientId = appointment.getPatient().getId();
        this.doctorId = appointment.getDoctor().getId();

    }



}
