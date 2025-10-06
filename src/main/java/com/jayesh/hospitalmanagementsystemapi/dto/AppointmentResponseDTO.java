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
public class AppointmentResponseDTO {
    private Long id;
    private String reason;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    public AppointmentResponseDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.reason = appointment.getReason();
        this.appointmentDate = appointment.getAppointmentDate();
        this.appointmentTime = appointment.getAppointmentTime();
    }

}
