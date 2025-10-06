package com.jayesh.hospitalmanagementsystemapi.repository;

import com.jayesh.hospitalmanagementsystemapi.dto.DoctorDTO;
import com.jayesh.hospitalmanagementsystemapi.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long> {

    List<Doctor> findAllDoctorByDoctorSpecialty(String doctorSpecialty);
    List<Doctor> findAllDoctorByDoctorName(String doctorName);
}
