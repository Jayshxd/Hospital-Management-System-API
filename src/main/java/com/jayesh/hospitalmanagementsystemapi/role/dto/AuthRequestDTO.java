package com.jayesh.hospitalmanagementsystemapi.role.dto;
import com.jayesh.hospitalmanagementsystemapi.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {
    private String username;
    private String password;
    private Set<String> roles;
}
