package com.jayesh.hospitalmanagementsystemapi.entity;

import com.jayesh.hospitalmanagementsystemapi.dto.AppointmentDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(
        name="appointments"
)
@Getter
@Setter
@NoArgsConstructor
public class Appointment{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 500)
    private String reason;
    @Column(nullable = false)
    private LocalDate appointmentDate;
    @Column(nullable = false)
    private LocalTime appointmentTime;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id",
            nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Doctor doctor;

    public Appointment(String reason, LocalDate appointmentDate, LocalTime appointmentTime) {
        this.reason = reason;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public Appointment(AppointmentDTO appointmentDTO){
        this.reason = appointmentDTO.getReason();
        this.appointmentDate = appointmentDTO.getAppointmentDate();
        this.appointmentTime = appointmentDTO.getAppointmentTime();
    }
}
