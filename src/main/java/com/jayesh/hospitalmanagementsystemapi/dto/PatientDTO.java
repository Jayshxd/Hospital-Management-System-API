package com.jayesh.hospitalmanagementsystemapi.dto;

import com.jayesh.hospitalmanagementsystemapi.entity.Patient;
import com.jayesh.hospitalmanagementsystemapi.enums.BloodGroups;
import com.jayesh.hospitalmanagementsystemapi.enums.Gender;
import com.jayesh.hospitalmanagementsystemapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientDTO {
    private String name;
    private BloodGroups bloodGroup;
    private Gender gender;
    private Status status;

    public PatientDTO(Patient patient){
        this.name=patient.getName();
        this.bloodGroup=patient.getBloodGroup();
        this.gender=patient.getGender();
        this.status=patient.getStatus();
    }


}
