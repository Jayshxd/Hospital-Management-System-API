package com.jayesh.hospitalmanagementsystemapi.role.service;

import com.jayesh.hospitalmanagementsystemapi.role.dto.AuthRequestDTO;
import com.jayesh.hospitalmanagementsystemapi.role.dto.AuthResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.role.entity.Role;
import com.jayesh.hospitalmanagementsystemapi.role.entity.User;
import com.jayesh.hospitalmanagementsystemapi.role.repo.RoleRepo;
import com.jayesh.hospitalmanagementsystemapi.role.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo  userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO userAndRoleRegister(AuthRequestDTO req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        Set<Role> roles = new HashSet<Role>();
        for(String role : req.getRoles()){
            Role roleName = roleRepo.findByName(role)
                    .orElseGet(
                            ()-> roleRepo.save(new Role(role))
                    );
            roles.add(roleName);
        }
        user.setRoles(roles);
        userRepo.save(user);
        return new AuthResponseDTO(user.getUsername(),"registered successfully");

    }

    @Transactional
    public List<AuthResponseDTO> userAndRoleListInBulk(List<AuthRequestDTO> authRequestDTOList) {
        List<AuthResponseDTO> authResponseDTOList = new ArrayList<>();
        for (AuthRequestDTO req : authRequestDTOList) {
            AuthResponseDTO response = userAndRoleRegister(req);
            authResponseDTOList.add(response);
        }
        return authResponseDTOList;
    }



}
