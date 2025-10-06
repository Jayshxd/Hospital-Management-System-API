package com.jayesh.hospitalmanagementsystemapi.dto;

import com.jayesh.hospitalmanagementsystemapi.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private String doctorName;
    private String doctorSpecialty;

    public DoctorDTO(Doctor doctor){
        this.doctorName=doctor.getDoctorName();
        this.doctorSpecialty=doctor.getDoctorSpecialty();
    }
}
