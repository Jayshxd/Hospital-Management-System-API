package com.jayesh.hospitalmanagementsystemapi.role.config;

import com.jayesh.hospitalmanagementsystemapi.role.entity.User;
import com.jayesh.hospitalmanagementsystemapi.role.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new org.springframework.security.core.userdetails.User(
                myUser.getUsername(),
                myUser.getPassword(),
                myUser.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_"+role.getName().toUpperCase())
                ).collect(Collectors.toList())
        );

    }
}
