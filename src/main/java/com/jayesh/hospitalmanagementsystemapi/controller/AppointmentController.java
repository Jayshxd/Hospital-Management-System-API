package com.jayesh.hospitalmanagementsystemapi.controller;

import com.jayesh.hospitalmanagementsystemapi.dto.AppointmentDTO;
import com.jayesh.hospitalmanagementsystemapi.dto.AppointmentResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.services.AppointmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentServices appointmentServices;
    @Autowired
    public AppointmentController(AppointmentServices appointmentServices) {
        this.appointmentServices = appointmentServices;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments(){
        List<AppointmentResponseDTO> appointmentResponseDTOS= appointmentServices.getListOfAppointments();
        return ResponseEntity.ok().body(appointmentResponseDTOS);
    }
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(
            @RequestBody AppointmentDTO appointmentDTO){
        AppointmentResponseDTO temp = appointmentServices.bookAppointment(appointmentDTO);
        return ResponseEntity.ok().body(temp);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(
            @PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO
    ){
        AppointmentResponseDTO temp = appointmentServices.rescheduleAppointment(id, appointmentDTO);
        return ResponseEntity.ok().body(temp);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(
            @PathVariable Long id
    ){
        appointmentServices.cancelAppointment(id);
        return ResponseEntity.ok().build();
    }




}
