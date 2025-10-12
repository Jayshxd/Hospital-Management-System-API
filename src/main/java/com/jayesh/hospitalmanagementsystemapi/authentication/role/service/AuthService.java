package com.jayesh.hospitalmanagementsystemapi.authentication.role.service;

import com.jayesh.hospitalmanagementsystemapi.authentication.role.dto.AuthRequestDTO;
import com.jayesh.hospitalmanagementsystemapi.authentication.role.dto.AuthResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.authentication.role.dto.LoginRequestDTO;
import com.jayesh.hospitalmanagementsystemapi.authentication.role.dto.LoginResponseDTO;
import com.jayesh.hospitalmanagementsystemapi.authentication.role.entity.Role;
import com.jayesh.hospitalmanagementsystemapi.authentication.role.entity.User;
import com.jayesh.hospitalmanagementsystemapi.authentication.role.repo.RoleRepo;
import com.jayesh.hospitalmanagementsystemapi.authentication.role.repo.UserRepo;
import com.jayesh.hospitalmanagementsystemapi.authentication.role.util.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;

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


    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );

        User user = (User)authentication.getPrincipal();
        String jwt = authUtil.generateAccessToken(user);
        return new LoginResponseDTO(jwt,user.getId());

    }
}
