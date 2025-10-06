package com.jayesh.hospitalmanagementsystemapi.dto;

import com.jayesh.hospitalmanagementsystemapi.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {
    private Long id;
    private String doctorName;
    private String doctorSpecialty;

    public DoctorResponseDTO(Doctor doctor){
        this.id = doctor.getId();
        this.doctorName=doctor.getDoctorName();
        this.doctorSpecialty=doctor.getDoctorSpecialty();
    }
}
