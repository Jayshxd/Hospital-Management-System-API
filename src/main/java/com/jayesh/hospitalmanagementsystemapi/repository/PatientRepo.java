package com.jayesh.hospitalmanagementsystemapi.repository;

import com.jayesh.hospitalmanagementsystemapi.entity.Patient;
import com.jayesh.hospitalmanagementsystemapi.enums.BloodGroups;
import com.jayesh.hospitalmanagementsystemapi.enums.Gender;
import com.jayesh.hospitalmanagementsystemapi.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Long> {
    List<Patient> findAllPatientByName(String name);
    List<Patient> findAllPatientByBloodGroup(BloodGroups bloodGroup);
    List<Patient> findAllPatientByGender(Gender gender);
    List<Patient> findAllPatientByStatus(Status status);



}
