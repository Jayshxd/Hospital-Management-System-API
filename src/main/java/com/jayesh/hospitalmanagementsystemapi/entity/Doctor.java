package com.jayesh.hospitalmanagementsystemapi.entity;

import com.jayesh.hospitalmanagementsystemapi.dto.DoctorDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,name = "doctor_name")
    private String doctorName;
    @Column(nullable = false)
    private String doctorSpecialty;
    @CreationTimestamp
    private LocalDateTime createdAt;



    @OneToMany(mappedBy = "doctor",fetch =  FetchType.LAZY)
    List<Appointment> appointments;


    public Doctor(String name, String specialty) {
        this.doctorName = name;
        this.doctorSpecialty = specialty;
    }

    public Doctor(DoctorDTO doctorDTO) {
        this.doctorName = doctorDTO.getDoctorName();
        this.doctorSpecialty = doctorDTO.getDoctorSpecialty();

    }
}
