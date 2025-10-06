package com.jayesh.hospitalmanagementsystemapi.entity;


import com.jayesh.hospitalmanagementsystemapi.dto.PatientDTO;
import com.jayesh.hospitalmanagementsystemapi.enums.BloodGroups;
import com.jayesh.hospitalmanagementsystemapi.enums.Gender;
import com.jayesh.hospitalmanagementsystemapi.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        indexes = {
                @Index(name = "idx_patient_name",columnList = "patient_name")
        }
)
@Getter
@Setter

@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="patient_name",nullable = false)
    private String name;

    @Column(name = "patient_blood_group",nullable = false)
    @Enumerated(EnumType.STRING)
    private BloodGroups bloodGroup;

    @Column(name = "patient_gender",nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "created_at",nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;


    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id",unique = true)
    private Insurance insurance;

    @OneToMany(mappedBy = "patient",fetch =  FetchType.LAZY)
    List<Appointment> appointments;



    public Patient(String name, BloodGroups bloodGroup, Gender gender,Status dischargeStatus) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.gender = gender;
        this.status = dischargeStatus;

    }

    public Patient(PatientDTO  patientDTO) {
        this.name = patientDTO.getName();
        this.bloodGroup = patientDTO.getBloodGroup();
        this.gender = patientDTO.getGender();
        this.status = patientDTO.getStatus();
    }

}
