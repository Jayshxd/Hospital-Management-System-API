package com.jayesh.hospitalmanagementsystemapi.role.controller;

import com.jayesh.hospitalmanagementsystemapi.role.dto.AuthRequestDTO;
import com.jayesh.hospitalmanagementsystemapi.role.dto.AuthResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.role.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> post(@RequestBody AuthRequestDTO authRequestDTO) {
        AuthResponseDTO response =authService.userAndRoleRegister(authRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("")
    public ResponseEntity<List<AuthResponseDTO>> post(@RequestBody
                                                      List<AuthRequestDTO> authRequestDTOList) {
        List<AuthResponseDTO> authResponseDTOList = authService.userAndRoleListInBulk(authRequestDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponseDTOList);
    }
}
